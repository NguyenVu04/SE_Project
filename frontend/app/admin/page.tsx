'use client';
import React, { useEffect, useState } from "react";
import SideBar from "./sidebar";
import TopBar from "./topbar";
import SummaryCards from "./summarycards";
import StudentList from "./studentlist";
import SpsoList from "./spsolist";
import getAllStudents from "@/lib/get-all-students";
import getAllSPSOs from "@/lib/get-all-spsos";
import getUserId from "@/lib/user-id";
import { redirect } from "next/navigation";
import Image from "next/image";

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
  const [id, setId] = useState<string | null>(null);

  const [students, setStudents] = React.useState<Student[]>([]);
  const [SPSOs, setSPSOs] = React.useState<SPSO[]>([]);

  useEffect(() => {
    getUserId("admin")
      .then((data) => {
        setId(data);
      })
      .catch(() => {
        redirect("/");
      })
  }, [])

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

  if (!id) {
    return (
      <div className="w-screen h-screen flex justify-center items-center">
        <Image src="/hcmut.svg" alt="Mô tả ảnh" width={64} height={64} />
      </div>
    )
  }

  return (
    <div className="grid grid-cols-[16rem_auto]">
      {/* Sidebar and Topbar */}
      <SideBar />
      <TopBar onAddSPSO={setSPSOs} onAddStudent={setStudents} />
      {/* Main page  */}
      <div className="m-4 pt-20 pl-2 w-auto">
        <SummaryCards cards_prob={
          [{ title: 'Số sinh viên', value: students.length },
          { title: 'Số nhân viên quản lý', value: SPSOs.length }]} />
        <div className="grid grid-cols-2 gap-4 mt-4">
          <StudentList students_prob={students} onDeleteStudent={setStudents} />
          <SpsoList spsos_prob={SPSOs} onDeleteSpso={setSPSOs} />
        </div>
      </div>
    </div>
  );
}