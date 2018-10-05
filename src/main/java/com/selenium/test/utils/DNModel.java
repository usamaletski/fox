package com.selenium.test.utils;

import com.selenium.test.pages.*;
import com.selenium.test.pages.desktop.*;

/**
 * A model of the application under test. It contains pages of the application. Those
 * objects, in turn, contain objects representing controls and methods on each page.
 *
 * Created by maria on 1/30/17.
 */
public class DNModel {
    protected static DNModel dn;

    public MobileLoginPage mLoginPage;
    public MobileCalendarPage mCalendar;
    public MobileControlPanelPage mControlPanel;
    public MobileDetailedEntryPage mDetailedEntry;
    public MobileMySchedulePage mMySchedule;
    public MobileStoriesPage mStories;

    public DesktopLoginPage dLoginPage;
    public DesktopDashboardPage dDashboard;
    public DesktopSettingsPage dSettings;
    public DesktopTimelinePage dTimeline;
    public DesktopTopicsPage dTopic;
    public DesktopPublicationPlatformsPage dPublicationPlatform;

    public static DNModel getInstance() throws Exception {
        if (dn == null) dn = new DNModel();
        return dn;
    }

    public void init() throws Exception {
        // pages
        mLoginPage = MobileLoginPage.getInstance();
        mCalendar = MobileCalendarPage.getInstance();
        mControlPanel = MobileControlPanelPage.getInstance();
        mDetailedEntry = MobileDetailedEntryPage.getInstance();
        mMySchedule = MobileMySchedulePage.getInstance();
        mStories = MobileStoriesPage.getInstance();

        dLoginPage = DesktopLoginPage.getInstance();
        dDashboard = DesktopDashboardPage.getInstance();
        dSettings = DesktopSettingsPage.getInstance();
        dTimeline = DesktopTimelinePage.getInstance();
        dTopic = DesktopTopicsPage.getInstance();
        dPublicationPlatform = DesktopPublicationPlatformsPage.getInstance();
    }

}