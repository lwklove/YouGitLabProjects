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

import com.intellij.ui.components.JBCheckBox;
import com.intellij.util.ui.FormBuilder;
import com.intellij.util.ui.UI;

import javax.swing.*;

/**
 * Provides a {@link JPanel} with widgets for the Telemetry settings.
 */
public class TelemetryComponent {

    private static final String DESCRIPTION =
            "Help You.com improve its products by sending anonymous data about features and plugins used, "
                    + "hardware and software configuration.<br/>"
                    + "<br/>"
                    + "Please note that this will not include personal data or any sensitive Information.<br/>"
                    + "The data sent complies with the <a href=\"https://developers.redhat.com/article/tool-data-collection\">Red Hat Privacy Policy</a>, you.com is logging data with their framework on our account.";

    private final JPanel panel;
    private final JBCheckBox enabled = new JBCheckBox("Send usage statistics");

    public TelemetryComponent() {
        this.panel = FormBuilder.createFormBuilder()
                .addComponent(createCommentedPanel(enabled, DESCRIPTION), 1)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    private JPanel createCommentedPanel(JComponent component, String comment) {
         // @See com.intellij.internal.ui.ComponentPanelTestAction for more details on how to create comment panels
        return UI.PanelFactory.panel(component)
                .withComment(comment)
                .createPanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    public JComponent getPreferredFocusedComponent() {
        return enabled;
    }

    public boolean isEnabled() {
        return enabled.isSelected();
    }

    public void setEnabled(boolean enabled) {
        this.enabled.setSelected(enabled);
    }

}
