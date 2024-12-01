'use client';
import React, { useEffect } from "react";
import SideBar from "./sidebar";
import TopBar from "./topbar";
import SummaryCards from "./summarycards";
import StudentList from "./studentlist";
import SpsoList from "./spsolist";
import getAllStudents from "@/lib/get-all-students";
import getAllSPSOs from "@/lib/get-all-spsos";

export type Student = {
  id: string;
  email: string;
  firstName: string;
  lastName: string;
  dateOfBirth: string;
  balance: number;
}

export type SPSO = {
  id: string;
  email: string;
  firstName: string;
  lastName: string;
}

export default function AdminPage() {

  const [students, setStudents] = React.useState<Student[]>([]);
  const [SPSOs, setSPSOs] = React.useState<SPSO[]>([]);

  useEffect(() => {
    getAllStudents().then((data) => {
      if (data) {
        setStudents(data);
      } else {
        alert("Có lỗi xảy ra! Vui lòng thử lại");
      }
    })
  }, []);

  useEffect(() => {
    getAllSPSOs().then((data) => {
      if (data) {
        setSPSOs(data);
      } else {
        alert("Có lỗi xảy ra! Vui lòng thử lại");
      }
    })
  }, []);

  return (
    <div className="grid grid-cols-[16rem_auto]">
      {/* Sidebar and Topbar */}
      <SideBar />
      <TopBar onAddSPSO={setSPSOs} onAddStudent={setStudents}/>
      {/* Main page  */}
      <div className="m-4 pt-20 pl-2 w-auto">
        <SummaryCards cards_prob={
          [{ title: 'Số sinh viên', value: students.length },
          { title: 'Số nhân viên quản lý', value: SPSOs.length }]} />
        <div className="grid grid-cols-2 gap-4 mt-4">
          <StudentList students_prob={students} onDeleteStudent={setStudents}/>
          <SpsoList spsos_prob={SPSOs} onDeleteSpso={setSPSOs} />
        </div>
      </div>
    </div>
  );
}