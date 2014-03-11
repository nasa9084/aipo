/*
 * Aipo is a groupware program developed by Aimluck,Inc.
 * Copyright (C) 2004-2011 Aimluck,Inc.
 * http://www.aipo.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.aimluck.eip.modules.screens;

import org.apache.jetspeed.services.logging.JetspeedLogFactoryService;
import org.apache.jetspeed.services.logging.JetspeedLogger;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.aimluck.eip.schedule.ScheduleHolidayFormData;
import com.aimluck.eip.schedule.util.ScheduleHolidayUtils;
import com.aimluck.eip.util.ALEipUtils;

/**
 * ワークフロー分類を処理するクラスです。 <br />
 * 
 */
public class ScheduleHolidayFormScreen extends ALVelocityScreen {

  /** logger */
  private static final JetspeedLogger logger = JetspeedLogFactoryService
    .getLogger(ScheduleHolidayFormScreen.class.getName());

  /**
   * 
   * @param rundata
   * @param context
   * @throws Exception
   */
  @Override
  protected void doOutput(RunData rundata, Context context) throws Exception {

    try {
      doSchedule_holiday_form(rundata, context);
    } catch (Exception ex) {
      logger.error("[ScheduleHolidayFormScreen] Exception.", ex);
      ALEipUtils.redirectDBError(rundata);
    }
  }

  protected void doSchedule_holiday_form(RunData rundata, Context context) {
    ScheduleHolidayFormData formData = new ScheduleHolidayFormData();
    formData.initField();
    formData.loadHolidayList(rundata, context);
    formData.doViewForm(this, rundata, context);

    String layout_template = "portlets/html/ja/ajax-schedule-holiday-form.vm";
    setTemplate(rundata, context, layout_template);
  }

  /**
   * @return
   */
  @Override
  protected String getPortletName() {
    return ScheduleHolidayUtils.SCHEDULE_HOLIDAY_PORTLET_NAME;
  }
}
