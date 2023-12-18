package nl.fontysS3_project.business;

import nl.fontysS3_project.domain.MapStats;
import nl.fontysS3_project.domain.OverallStats;

public interface StatsManager {
    OverallStats getOverallStats(long userid);

    MapStats getMapStats(long userid, long mapid);
}
