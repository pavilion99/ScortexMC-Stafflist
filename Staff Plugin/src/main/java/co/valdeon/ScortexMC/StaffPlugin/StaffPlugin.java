package co.valdeon.ScortexMC.StaffPlugin;

import co.valdeon.ScortexMC.StaffPlugin.Commands.StafflistAdminCmd;
import co.valdeon.ScortexMC.StaffPlugin.Commands.StaffCmd;
import co.valdeon.ScortexMC.StaffPlugin.Hooks.GMHook;
import co.valdeon.ScortexMC.StaffPlugin.Listeners.GMUserListener;
import co.valdeon.ScortexMC.StaffPlugin.Listeners.PlayerJoinListener;
import co.valdeon.ScortexMC.StaffPlugin.Listeners.PlayerQuitListener;
import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class StaffPlugin extends JavaPlugin{

    public static HashMap<Player, String> staffList = new HashMap<>();
    private static GroupManager gm;
    private static GMHook gmHook;

    public static String[] staffRanks;

    public static String staff1Message;
    public static String staff0OrMoreMessage;
    public static String messageColor;
    public static String noStaffMessage;
    public static String messageColor2;
    public static String separator;
    public static boolean onlyPrefix;
    public static boolean lineBreaks;
    public static String usernameColor;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        reload();

        gmHook = new GMHook(Bukkit.getPluginManager().getPlugin("GroupManager"));
        gm = gmHook.getGM();

        for(Player p : Bukkit.getOnlinePlayers()) {
            AnjoPermissionsHandler h = gm.getWorldsHolder().getWorldPermissions(p);

            if(h == null)
                continue;

            for(String s : staffRanks) {
                if(s.equals(h.getGroup(p.getName())))
                staffList.put(p, GMHook.getProperTitle(p, h));
            }
        }

        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new GMUserListener(), this);

        getCommand("staff").setExecutor(new StaffCmd());
        getCommand("stafflistadmin").setExecutor(new StafflistAdminCmd(this));
    }

    @Override
    public void onDisable() {
        staffList = null;
        gm = null;
        gmHook = null;
        staffRanks = null;
        staff1Message = null;
        staff0OrMoreMessage = null;
        messageColor = null;
        noStaffMessage = null;
        separator = null;
        messageColor2 = null;
        usernameColor = null;
    }

    public static GMHook getGMHook() {
        return gmHook;
    }

    public void reload() {
        reloadConfig();
        staffRanks = getConfig().getStringList("staffranks").toArray(new String[0]);
        staff1Message = getConfig().getString("success1");
        staff0OrMoreMessage = getConfig().getString("successmanyor0");
        noStaffMessage = getConfig().getString("failure");
        messageColor = getConfig().getString("messagecolor");
        messageColor2 = getConfig().getString("messagesubcolor");
        separator = getConfig().getString("separator");
        onlyPrefix = getConfig().getBoolean("onlyPrefix");
        lineBreaks = getConfig().getBoolean("lineBreaks");
        usernameColor = getConfig().getString("usernameColor");
    }

}
