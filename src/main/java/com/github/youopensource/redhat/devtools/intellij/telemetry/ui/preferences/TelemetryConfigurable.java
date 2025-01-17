/*******************************************************************************
 * Copyright (c) 2021 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package com.github.youopensource.redhat.devtools.intellij.telemetry.ui.preferences;

import com.github.youopensource.redhat.devtools.intellij.telemetry.core.configuration.TelemetryConfiguration;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.options.SearchableConfigurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.IOException;

/**
 * Controller for telemetry settings.
 */
public class TelemetryConfigurable implements SearchableConfigurable {

    /* plugin.xml > applicationConfigurable > id */
    public static final String ID = "tools.preferences.redhat.telemetry";

    private static final Logger LOGGER = Logger.getInstance(TelemetryConfigurable.class);

    private TelemetryComponent component;
    private final TelemetryConfiguration configuration = TelemetryConfiguration.getInstance();

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Red Hat Telemetry";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return component.getPreferredFocusedComponent();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        this.component = new TelemetryComponent();
        return component.getPanel();
    }

    @Override
    public boolean isModified() {
        boolean modified = false;
        modified |= (component.isEnabled() != configuration.isEnabled());
        return modified;
    }

    @Override
    public void apply() {
        configuration.setEnabled(component.isEnabled());
        try {
            configuration.save();
        } catch (IOException e) {
            LOGGER.warn("Could not save telemetry configuration.", e);
        }
    }

    @Override
    public void reset() {
        component.setEnabled(configuration.isEnabled());
    }

    @Override
    public void disposeUIResources() {
        component = null;
    }

    @NotNull
    @Override
    public String getId() {
        return ID;
    }
}
