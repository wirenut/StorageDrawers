package com.jaquadro.minecraft.storagedrawers.block;

import com.jaquadro.minecraft.storagedrawers.api.storage.IDrawer;
import com.jaquadro.minecraft.storagedrawers.api.storage.IDrawerGroup;
import com.jaquadro.minecraft.storagedrawers.api.storage.INetworked;
import com.jaquadro.minecraft.storagedrawers.block.tile.TileEntityDrawers;
import com.jaquadro.minecraft.storagedrawers.block.tile.TileEntityDrawersComp;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockCompDrawers extends BlockDrawers implements INetworked
{
    public static final EnumProperty<EnumCompDrawer> SLOTS = EnumProperty.create("slots", EnumCompDrawer.class);

    //@SideOnly(Side.CLIENT)
    //private StatusModelData statusInfo;

    public BlockCompDrawers (int storageUnits, Block.Properties properties) {
        super(3, false, storageUnits, properties);
        this.setDefaultState(getDefaultState()
            .with(SLOTS, EnumCompDrawer.OPEN1));
    }

    public BlockCompDrawers (Block.Properties properties) {
        this(32, properties);
    }

    @Override
    protected void fillStateContainer (StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(SLOTS);
    }

    /*@Override
    @SideOnly(Side.CLIENT)
    public void initDynamic () {
        ResourceLocation location = new ResourceLocation(StorageDrawers.MOD_ID + ":models/dynamic/compDrawers.json");
        statusInfo = new StatusModelData(3, location);
    }*/

    /*@Override
    public StatusModelData getStatusInfo (IBlockState state) {
        return statusInfo;
    }*/

    @Override
    protected int getDrawerSlot (Direction side, Vector3d hit) {
        if (hitTop(hit.y))
            return 0;

        if (hitLeft(side, hit.x, hit.z))
            return 1;
        else
            return 2;
    }

    @Override
    public void onBlockPlacedBy (World world, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {
        super.onBlockPlacedBy(world, pos, state, entity, stack);

        TileEntityDrawers tile = getTileEntity(world, pos);
        if (tile != null) {
            IDrawerGroup group = tile.getGroup();
            for (int i = group.getDrawerCount() - 1; i >= 0; i--) {
                if (!group.getDrawer(i).isEmpty()) {
                    world.setBlockState(pos, state.with(SLOTS, EnumCompDrawer.byOpenSlots(i + 1)), 3);
                    break;
                }
            }
        }
    }

    /*@Override
    public IBlockState getStateForPlacement (World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return getDefaultState();
    }*/

    /*@Override
    public BlockType retrimType () {
        return null;
    }*/

    @Override
    public TileEntityDrawers createTileEntity (BlockState state, IBlockReader world) {
        return new TileEntityDrawersComp.Slot3();
    }

    /*@Override
    protected BlockStateContainer createBlockState () {
        return new ExtendedBlockState(this, new IProperty[] { SLOTS, FACING }, new IUnlistedProperty[] { STATE_MODEL });
    }*/
}
