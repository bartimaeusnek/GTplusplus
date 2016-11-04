package gtPlusPlus.xmod.gregtech.common.tileentities.generators;

import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.GregTech_API;
import gregtech.api.enums.*;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Recipe;
import gtPlusPlus.xmod.gregtech.api.metatileentity.implementations.base.generators.GregtechRocketFuelGeneratorBase;
import gtPlusPlus.xmod.gregtech.common.blocks.textures.TexturesGtBlock;
import net.minecraft.item.ItemStack;

public class GregtechMetaTileEntityDoubleFuelGeneratorBase extends GregtechRocketFuelGeneratorBase {

	public int mEfficiency;

	public GregtechMetaTileEntityDoubleFuelGeneratorBase(final int aID, final String aName, final String aNameRegional,
			final int aTier) {
		super(aID, aName, aNameRegional, aTier, "Requires two liquid Fuels. Fuel A is Fastburn, Fuel B is slowburn.",
				new ITexture[0]);
		this.onConfigLoad();
	}

	public GregtechMetaTileEntityDoubleFuelGeneratorBase(final String aName, final int aTier, final String aDescription,
			final ITexture[][][] aTextures) {
		super(aName, aTier, aDescription, aTextures);
		this.onConfigLoad();
	}

	@Override
	public ITexture[] getBack(final byte aColor) {
		return new ITexture[] {
				super.getBack(aColor)[0], this.getCasingTexture(),
				new GT_RenderedTexture(TexturesGtBlock.Overlay_Machine_Vent)
		};
	}

	@Override
	public ITexture[] getBackActive(final byte aColor) {
		return new ITexture[] {
				super.getBackActive(aColor)[0], this.getCasingTexture(),
				new GT_RenderedTexture(TexturesGtBlock.Overlay_Machine_Vent_Fast)
		};
	}

	@Override
	public ITexture[] getBottom(final byte aColor) {
		return new ITexture[] {
				super.getBottom(aColor)[0], new GT_RenderedTexture(TexturesGtBlock.Casing_Machine_Simple_Bottom)
		};
	}

	@Override
	public ITexture[] getBottomActive(final byte aColor) {
		return new ITexture[] {
				super.getBottomActive(aColor)[0], new GT_RenderedTexture(TexturesGtBlock.Casing_Machine_Simple_Bottom)
		};
	}

	@Override
	public int getCapacity() {
		return 32000;
	}

	private GT_RenderedTexture getCasingTexture() {
		if (this.mTier <= 4) {
			return new GT_RenderedTexture(TexturesGtBlock.Casing_Machine_Simple_Top);
		}
		else if (this.mTier == 5) {

			return new GT_RenderedTexture(TexturesGtBlock.Casing_Machine_Advanced);
		}
		else if (this.mTier >= 6) {

			return new GT_RenderedTexture(TexturesGtBlock.Casing_Machine_Ultra);
		}
		return new GT_RenderedTexture(TexturesGtBlock.Casing_Machine_Simple_Top);
	}

	@Override
	public int getEfficiency() {
		return this.mEfficiency;
	}

	@Override
	public ITexture[] getFront(final byte aColor) {
		return new ITexture[] {
				super.getFront(aColor)[0], this.getCasingTexture(),
				Textures.BlockIcons.OVERLAYS_ENERGY_OUT_MULTI[this.mTier]
		};
	}

	@Override
	public ITexture[] getFrontActive(final byte aColor) {
		return new ITexture[] {
				super.getFrontActive(aColor)[0], this.getCasingTexture(),
				Textures.BlockIcons.OVERLAYS_ENERGY_OUT_MULTI[this.mTier]
		};
	}

	@Override
	public int getFuelValue(final ItemStack aStack) {
		int rValue = Math.max(GT_ModHandler.getFuelCanValue(aStack) * 6 / 5, super.getFuelValue(aStack));
		if (ItemList.Fuel_Can_Plastic_Filled.isStackEqual(aStack, false, true)) {
			rValue = Math.max(rValue, GameRegistry.getFuelValue(aStack) * 3);
		}
		return rValue;
	}

	@Override
	public GT_Recipe.GT_Recipe_Map getRecipes() {
		return GT_Recipe.GT_Recipe_Map.sDieselFuels;
	}

	@Override
	public ITexture[] getSides(final byte aColor) {
		return new ITexture[] {
				super.getSides(aColor)[0], this.getCasingTexture(),
				new GT_RenderedTexture(TexturesGtBlock.Overlay_Machine_Diesel_Horizontal)
		};
	}

	@Override
	public ITexture[] getSidesActive(final byte aColor) {
		return new ITexture[] {
				super.getSidesActive(aColor)[0], this.getCasingTexture(),
				new GT_RenderedTexture(TexturesGtBlock.Overlay_Machine_Diesel_Horizontal_Active)
		};
	}

	@Override
	public ITexture[] getTop(final byte aColor) {
		return new ITexture[] {
				super.getTop(aColor)[0], new GT_RenderedTexture(TexturesGtBlock.Casing_Machine_Redstone_Off)
		};
	}

	@Override
	public ITexture[] getTopActive(final byte aColor) {
		return new ITexture[] {
				super.getTopActive(aColor)[0], new GT_RenderedTexture(TexturesGtBlock.Casing_Machine_Redstone_On)
		};
	}

	@Override
	public boolean isOutputFacing(final byte aSide) {
		return aSide == this.getBaseMetaTileEntity().getFrontFacing();
	}

	@Override
	public MetaTileEntity newMetaEntity(final IGregTechTileEntity aTileEntity) {
		return new GregtechMetaTileEntityDoubleFuelGeneratorBase(this.mName, this.mTier, this.mDescription,
				this.mTextures);
	}

	public void onConfigLoad() {
		this.mEfficiency = GregTech_API.sMachineFile.get(ConfigCategories.machineconfig,
				"RocketEngine.efficiency.tier." + this.mTier, 100 - this.mTier * 8);
	}
}