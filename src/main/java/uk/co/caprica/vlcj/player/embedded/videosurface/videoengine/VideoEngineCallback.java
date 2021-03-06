/*
 * This file is part of VLCJ.
 *
 * VLCJ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VLCJ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VLCJ.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2009-2020 Caprica Software Limited.
 */

package uk.co.caprica.vlcj.player.embedded.videosurface.videoengine;

import com.sun.jna.Pointer;
import uk.co.caprica.vlcj.binding.internal.libvlc_video_setup_device_cfg_t;
import uk.co.caprica.vlcj.binding.internal.libvlc_video_setup_device_info_t;

/**
 * Specification for a component that provides a bridge from the native video engine to a rendering surface.
 */
public interface VideoEngineCallback {

    /**
     * Client setup.
     *
     * @param opaque opaque data pointer
     * @param deviceConfiguration device configuration
     * @param deviceInformation output device information
     * @return <code>true</code> on success; <code>false</code> on error
     */
    boolean onSetup(Pointer opaque, libvlc_video_setup_device_cfg_t deviceConfiguration, libvlc_video_setup_device_info_t deviceInformation);

    /**
     * Client clean-up.
     *
     * @param opaque opaque data pointer
     */
    void onCleanup(Pointer opaque);

    /**
     * Set the resize callback.
     *
     * @param opaque opaque data pointer
     * @param report_size_change  callback
     * @param report_opaque opaque data pointer for the callback
     * @return callback, or <code>null</code>
     */
    Pointer onSetResizeCallback(Pointer opaque, Pointer report_size_change, Pointer report_opaque);

    /**
     * Update the video output with new dimensions.
     *
     * @param opaque opaque data pointer
     * @param width new video width
     * @param height new video height
     * @return <code>true</code> on success; <code>false</code> on error
     */
    boolean onUpdateOutput(Pointer opaque, int width, int height);

    /**
     * A batch of native rendering calls finished.
     *
     * @param opaque opaque data pointer
     */
    void onSwap(Pointer opaque);

    /**
     * Set or unset the current rendering context.
     *
     * @param opaque opaque data pointer
     * @param enter <code>true</code> if the context should be set; <code>false</code> if it should be un-set
     * @return <code>true</code> on success; <code>false</code> on error
     */
    boolean onMakeCurrent(Pointer opaque, boolean enter);

    /**
     * Get a pointer to a native procedure.
     *
     * @param opaque opaque data pointer
     * @param functionName native procedure name
     * @return native procedure address
     */
    long onGetProcAddress(Pointer opaque, String functionName);
}
