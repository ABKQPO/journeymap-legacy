package journeymap.client.ui.waypoint;

import journeymap.client.Constants;
import journeymap.client.forge.helper.ForgeHelper;
import journeymap.client.model.Waypoint;
import journeymap.client.ui.component.Button;

public class WaypointModeButton extends Button
{
    private int modeIndex;

    public WaypointModeButton(Waypoint waypoint)
    {
        super("");
        this.modeIndex = getModeIndex(waypoint);
        refresh();
    }

    public void cycle()
    {
        modeIndex = (modeIndex + 1) % 4;
        refresh();
    }

    public void cyclePrevious()
    {
        modeIndex = (modeIndex + 3) % 4;
        refresh();
    }

    public void applyTo(Waypoint waypoint)
    {
        switch (modeIndex)
        {
            case 0:
                waypoint.setWaypointMode(true, true, false);
                break;
            case 1:
                waypoint.setWaypointMode(false, true, false);
                break;
            case 2:
                waypoint.setWaypointMode(true, false, true);
                break;
            default:
                waypoint.setWaypointMode(true, false, false);
                break;
        }
    }

    @Override
    public void refresh()
    {
        displayString = Constants.getString(getTranslationKey());
        fitWidth(ForgeHelper.INSTANCE.getFontRenderer());
    }

    private static int getModeIndex(Waypoint waypoint)
    {
        if (waypoint.isTemporary())
        {
            return 2;
        }
        if (waypoint.isDestination())
        {
            return 3;
        }
        return waypoint.isEnable() ? 0 : 1;
    }

    private String getTranslationKey()
    {
        switch (modeIndex)
        {
            case 0:
                return "jm.waypoint.mode_open";
            case 1:
                return "jm.waypoint.mode_closed";
            case 2:
                return "jm.waypoint.mode_temporary";
            default:
                return "jm.waypoint.mode_destination";
        }
    }
}
