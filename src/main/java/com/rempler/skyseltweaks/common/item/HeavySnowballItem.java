package com.rempler.skyseltweaks.common.item;

import com.rempler.skyseltweaks.common.utils.SkySelConfigs;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class HeavySnowballItem extends Item {
    private boolean bool = false;
    public HeavySnowballItem(Properties pProperties) {
        super(pProperties);
    }
    public HeavySnowballItem(Properties pProperties, boolean bool) {
        super(pProperties);
        this.bool = bool;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!pLevel.isClientSide) {
            Snowball snowball = new Snowball(pLevel, pPlayer) {
                @Override
                protected void onHitEntity(EntityHitResult pResult) {
                    Entity entity = pResult.getEntity();
                    int dmg = SkySelConfigs.HEAVY_SNOWBALL_DAMAGE.get().intValue();
                    if (SkySelConfigs.HURT_PLAYERS.get()) {
                        dmg = 0;
                    }
                    entity.hurt(DamageSource.thrown(this, this.getOwner()), dmg);
                }
            };
            snowball.setItem(itemstack);
            snowball.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 1.0F);
            pLevel.addFreshEntity(snowball);
        }

        pPlayer.awardStat(Stats.ITEM_USED.get(this));
        if (!pPlayer.getAbilities().instabuild) {
            itemstack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }
}
