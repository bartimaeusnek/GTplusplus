package gtPlusPlus.xmod.gregtech.common.items;

import gregtech.api.GregTech_API;
import gregtech.api.enums.TC_Aspects;
import gregtech.api.enums.ToolDictNames;
import gregtech.api.items.GT_MetaGenerated_Tool;

import gtPlusPlus.xmod.gregtech.api.enums.GregtechToolDictNames;
import gtPlusPlus.xmod.gregtech.common.tools.TOOL_Gregtech_Choocher;

public class MetaGeneratedGregtechTools extends GT_MetaGenerated_Tool {

	public static final short SKOOKUM_CHOOCHER = 7734;
	public static MetaGeneratedGregtechTools INSTANCE;

	public MetaGeneratedGregtechTools() {
		super("plusplus.metatool.01");
		INSTANCE = this;
		GregTech_API.registerTool(
				this.addTool(
						SKOOKUM_CHOOCHER, "Skookum Choocher",
						"Can Really Chooch. Does a Skookum job at Hammering and Wrenching stuff.",
						new TOOL_Gregtech_Choocher(),
						new Object[]{GregtechToolDictNames.craftingToolSkookumChoocher,
								ToolDictNames.craftingToolHardHammer,
								ToolDictNames.craftingToolWrench,
								new TC_Aspects.TC_AspectStack(TC_Aspects.INSTRUMENTUM, 2L),
								new TC_Aspects.TC_AspectStack(TC_Aspects.FABRICO, 2L),
								new TC_Aspects.TC_AspectStack(TC_Aspects.ORDO, 2L)}),
				GregTech_API.sWrenchList);
	}
}
