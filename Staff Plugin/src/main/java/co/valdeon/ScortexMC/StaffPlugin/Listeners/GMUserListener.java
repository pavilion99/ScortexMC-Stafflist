package co.valdeon.ScortexMC.StaffPlugin.Listeners;

import co.valdeon.ScortexMC.StaffPlugin.Hooks.GMHook;
import co.valdeon.ScortexMC.StaffPlugin.StaffPlugin;
import org.anjocaido.groupmanager.events.GMUserEvent;
import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GMUserListener implements Listener {

    @EventHandler
    public void onEvent(GMUserEvent event) {
        Player p = event.getUser().getBukkitPlayer();
        AnjoPermissionsHandler h = StaffPlugin.getGMHook().getHandler(p);

        if(!event.getAction().equals(GMUserEvent.Action.USER_GROUP_CHANGED))
            return;

        if(StaffPlugin.staffList.containsKey(p)) {
            boolean userStillStaff = false;
            for(String s : StaffPlugin.staffRanks){
                if(s.equals(h.getGroup(p.getName()))){
                    userStillStaff = true;
                    break;
                }
            }
            if(!userStillStaff)
                StaffPlugin.staffList.remove(p);
            else
                StaffPlugin.staffList.put(p, GMHook.getProperTitle(p, h));
        }else{
            for(String s : StaffPlugin.staffRanks){
                if(s.equals(h.getGroup(p.getName())))
                    StaffPlugin.staffList.put(p, GMHook.getProperTitle(p, h));
            }
        }
    }

}
