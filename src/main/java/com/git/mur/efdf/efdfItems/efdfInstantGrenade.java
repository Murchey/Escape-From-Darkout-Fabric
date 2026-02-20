package com.git.mur.efdf.efdfItems;

import com.git.mur.efdf.Efdf;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;

public class efdfInstantGrenade extends ThrownItemEntity {

    private static final TrackedData<Integer> FUSE = DataTracker.registerData(efdfInstantGrenade.class, TrackedDataHandlerRegistry.INTEGER);
    private static final int DEFAULT_FUSE = 80;

    private int fuseTimer = DEFAULT_FUSE;
    private boolean inGround = false;

    public static final Item INSTANT_GRENADE = new GrenadeItem(new FabricItemSettings().maxCount(16));
    public static final EntityType<efdfInstantGrenade> INSTANT_GRENADE_PROJECTILE =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    new Identifier(Efdf.MODID, "instant_grenade_projectile"),
                    FabricEntityTypeBuilder.<efdfInstantGrenade>create(SpawnGroup.MISC, efdfInstantGrenade::new)
                            .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                            .trackRangeBlocks(64)
                            .trackedUpdateRate(1)
                            .forceTrackedVelocityUpdates(true)
                            .build()
            );

    public static void grenadeInit() {
        Registry.register(Registries.ITEM, new Identifier(Efdf.MODID, "instant_grenade"), INSTANT_GRENADE);
    }

    public efdfInstantGrenade(EntityType<? extends efdfInstantGrenade> entityType, World world) {
        super(entityType, world);
        this.noClip = false;
    }

    public efdfInstantGrenade(World world, LivingEntity owner) {
        super(INSTANT_GRENADE_PROJECTILE, owner, world);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(FUSE, DEFAULT_FUSE);
    }

    @Override
    public void tick() {
        if (!this.getWorld().isClient()) {
            this.fuseTimer--;
            this.dataTracker.set(FUSE, this.fuseTimer);
            
            if (this.fuseTimer <= 0) {
                this.explode();
                return;
            }
        }

        this.spawnFuseParticles();

        Vec3d prevPos = this.getPos();
        Vec3d prevVel = this.getVelocity();

        Vec3d velocity = this.getVelocity();
        double maxSpeed = 1.2;
        double speed = velocity.length();
        if (speed > maxSpeed) {
            velocity = velocity.multiply(maxSpeed / speed);
            this.setVelocity(velocity);
        }

        super.tick();

        if (!this.getWorld().isClient()) {
            checkAndFixClipping(prevPos, prevVel);
        }
    }

    private void checkAndFixClipping(Vec3d prevPos, Vec3d prevVel) {
        Box box = this.getBoundingBox();
        boolean isColliding = false;
        
        for (VoxelShape shape : this.getWorld().getBlockCollisions(this, box)) {
            isColliding = true;
            break;
        }
        
        if (isColliding || this.isInsideWall()) {
            this.setPosition(prevPos.x, prevPos.y + 0.1, prevPos.z);
            if (prevVel.y < 0) {
                this.setVelocity(prevVel.x * 0.2, Math.max(-prevVel.y * 0.3, 0.1), prevVel.z * 0.2);
            } else {
                this.setVelocity(prevVel.x * 0.2, prevVel.y * 0.3, prevVel.z * 0.2);
            }
            this.velocityDirty = true;
            this.inGround = true;
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            handleBlockCollision((BlockHitResult) hitResult);
        } else if (hitResult.getType() == HitResult.Type.ENTITY) {
            handleEntityCollision((EntityHitResult) hitResult);
        }
    }

    private void handleBlockCollision(BlockHitResult hitResult) {
        Vec3d velocity = this.getVelocity();
        Direction side = hitResult.getSide();

        double bounceFactor = 0.35;
        double friction = 0.85;

        double vx = velocity.x;
        double vy = velocity.y;
        double vz = velocity.z;

        switch (side.getAxis()) {
            case X: vx = -vx * bounceFactor; vy *= friction; vz *= friction; break;
            case Y: vx *= friction; vz *= friction; vy = -vy * bounceFactor; break;
            case Z: vx *= friction; vy *= friction; vz = -vz * bounceFactor; break;
        }

        this.setVelocity(vx, vy, vz);
        this.velocityDirty = true;

        double pushDistance = side == Direction.DOWN ? 0.5 : 0.3;
        Vec3d pushOut = Vec3d.of(side.getOpposite().getVector()).multiply(pushDistance);
        this.setPosition(
                this.getX() + pushOut.x,
                this.getY() + pushOut.y,
                this.getZ() + pushOut.z
        );

        if (this.getVelocity().lengthSquared() < 0.04) {
            this.setVelocity(0, 0, 0);
            this.inGround = true;
        }
    }

    private void handleEntityCollision(EntityHitResult hitResult) {
        hitResult.getEntity().damage(
                this.getDamageSources().thrown(this, this.getOwner()),
                1.0F
        );

        Vec3d vel = this.getVelocity();
        this.setVelocity(-vel.x * 0.3, -vel.y * 0.3, -vel.z * 0.3);
        this.velocityDirty = true;
    }

    private void explode() {
        World world = this.getWorld();

        if (world.isClient()) {
            for (int i = 0; i < 10; i++) {
                world.addParticle(ParticleTypes.LARGE_SMOKE,
                        this.getX(), this.getY(), this.getZ(),
                        world.random.nextGaussian() * 0.2,
                        world.random.nextDouble() * 0.2,
                        world.random.nextGaussian() * 0.2);
            }
            return;
        }

        world.createExplosion(
                this,
                this.getX(), this.getY(), this.getZ(),
                3.0f,
                World.ExplosionSourceType.TNT
        );

        world.playSound(null, this.getX(), this.getY(), this.getZ(),
                SoundEvents.ENTITY_GENERIC_EXPLODE,
                SoundCategory.BLOCKS,
                4.0F, (1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.2F) * 0.7F);

        this.discard();
    }

    private void spawnFuseParticles() {
        World world = this.getWorld();
        
        for (int i = 0; i < 2; i++) {
            world.addParticle(ParticleTypes.SMOKE,
                    this.getX() + world.random.nextGaussian() * 0.05,
                    this.getY() + 0.1 + world.random.nextDouble() * 0.1,
                    this.getZ() + world.random.nextGaussian() * 0.05,
                    world.random.nextGaussian() * 0.02,
                    0.03 + world.random.nextDouble() * 0.02,
                    world.random.nextGaussian() * 0.02);
        }

        int currentFuse = this.dataTracker.get(FUSE);
        if (currentFuse <= 20 && world.random.nextFloat() < 0.7f) {
            world.addParticle(ParticleTypes.FLAME,
                    this.getX(), this.getY(), this.getZ(),
                    0, 0, 0);
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putShort("Fuse", (short) this.fuseTimer);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("Fuse")) {
            this.fuseTimer = nbt.getShort("Fuse");
        }
    }

    @Override
    protected Item getDefaultItem() {
        return INSTANT_GRENADE;
    }

    @Override
    protected float getGravity() {
        return 0.04f;
    }

    public static class GrenadeItem extends Item {
        public GrenadeItem(Settings settings) {
            super(settings);
        }

        @Override
        public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
            ItemStack itemStack = user.getStackInHand(hand);

            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.NEUTRAL,
                    1.0F, 1.0F);

            if (!world.isClient) {
                efdfInstantGrenade grenade = new efdfInstantGrenade(world, user);
                grenade.setItem(itemStack);
                grenade.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.0F, 1.0F);
                world.spawnEntity(grenade);
            }

            user.incrementStat(Stats.USED.getOrCreateStat(this));
            if (!user.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }

            return TypedActionResult.success(itemStack, world.isClient());
        }
    }
}