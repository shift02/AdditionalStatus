package shift.additionalstatus.asm;

import java.io.File;
import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLCallHook;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

public class ASCore implements IFMLLoadingPlugin, IFMLCallHook {

    static File location;

    @Override
    public String[] getASMTransformerClass() {
        return new String[] {
                "shift.additionalstatus.asm.TransformerEntityPlayerSP",
                "shift.additionalstatus.asm.TransformerFoodStats" };
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;//DepLoader.class.getName();
    }

    @Override
    public void injectData(Map<String, Object> data) {
        if (data.containsKey("coremodLocation")) {
            location = (File) data.get("coremodLocation");
        }

        if (location == null) {

            location = new File((File) data.get("mcLocation"), "../bin");

            location.mkdir();

        }
    }

    @Override
    public Void call() {
        //DepLoader.load();

        return null;
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

}