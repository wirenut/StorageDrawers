package com.jaquadro.minecraft.storagedrawers.packs.natura.block;

import com.jaquadro.minecraft.storagedrawers.block.BlockTrim;
import com.jaquadro.minecraft.storagedrawers.packs.natura.core.ModBlocks;
import com.jaquadro.minecraft.storagedrawers.packs.natura.core.ModCreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class BlockTrimPack extends BlockTrim
{
    public BlockTrimPack (String name) {
        super(name);

        setCreativeTab(ModCreativeTabs.tabStorageDrawers);
    }

    @Override
    public void getSubBlocks (Item item, CreativeTabs creativeTabs, List list) {
        for (int i = 1; i < BlockDrawersPack.textureNames.length; i++)
            list.add(new ItemStack(item, 1, i));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon (int side, int meta) {
        return ModBlocks.fullDrawers1.getIcon(0, meta);
    }
}
