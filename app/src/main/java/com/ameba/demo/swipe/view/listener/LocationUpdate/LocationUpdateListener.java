package com.ameba.demo.swipe.view.listener.LocationUpdate;

import android.location.Location;

/**
 * Created by ameba on 16/5/19.
 */

public interface LocationUpdateListener {
    void OneShot(Location location);
    void Continous(Location location);
}
