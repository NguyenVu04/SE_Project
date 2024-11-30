'use client';
import React from "react";
import SideBar from "./sidebar";
import TopBar from "./topbar";
import SummaryCards from "./summarycards";
import StatisticsChart from './statisticschart';
import Notifications from './notifications';
import CurrentJobs from './currentjobs';
import RecentJobs from './recentjobs';
import PrinterList from './printerlist';
export default function AdminPage() {
  return (
    
    <div className="grid grid-cols-[16rem_auto]">
      {/* Sidebar and Topbar */}
      <SideBar/>
      <TopBar></TopBar>
      {/* Main page  */}
      <div className="grid grid-cols-2 lg:grid-cols-3 gap-6 mt-4 pt-20 pl-2 w-[calc(100%-64px)]">
        <div className="col-start-1 lg:col-span-2 space-y-4">
          <SummaryCards />
          <StatisticsChart />
          <RecentJobs />
        </div>
        <div className="space-y-5">
          {/* <Notifications /> */}
          {/* <CurrentJobs /> */}
          <PrinterList />
        </div>
      </div>
    </div>
  );
}