'use client';
import React from "react";
import SideBar from "./sidebar";
import TopBar from "./topbar";
import SummaryCards from "./summarycards";
import StudentList from "./studentlist";
import SpsoList from "./spsolist";
export default function AdminPage() {
    const students = [
        {
            "id": "1",
            "email": "student1@example.com",
            "firstName": "John",
            "lastName": "Doe",
            "dateOfBirth": "2000-05-15",
            "balance": 100
        },
        {
            "id": "2",
            "email": "student2@example.com",
            "firstName": "Jane",
            "lastName": "Smith",
            "dateOfBirth": "1999-10-20",
            "balance": 50
        },
        {
            "id": "3",
            "email": "student3@example.com",
            "firstName": "Alice",
            "lastName": "Brown",
            "dateOfBirth": "2001-03-10",
            "balance": 75
        },
        {
            "id": "4",
            "email": "student4@example.com",
            "firstName": "Bob",
            "lastName": "Johnson",
            "dateOfBirth": "1998-07-25",
            "balance": 0
        },
        {
            "id": "5",
            "email": "student5@example.com",
            "firstName": "Eve",
            "lastName": "Williams",
            "dateOfBirth": "2002-12-05",
            "balance": 150
        }
    ]
    const spsos = [
  {
    "id": "1",
    "email": "spso1@example.com",
    "firstName": "Alice",
    "lastName": "Johnson"
  },
  {
    "id": "2",
    "email": "spso2@example.com",
    "firstName": "Bob",
    "lastName": "Smith"
  },
  {
    "id": "3",
    "email": "spso3@example.com",
    "firstName": "Charlie",
    "lastName": "Brown"
  },
  {
    "id": "4",
    "email": "spso4@example.com",
    "firstName": "Diana",
    "lastName": "White"
  },
  {
    "id": "5",
    "email": "spso5@example.com",
    "firstName": "Edward",
    "lastName": "Green"
  }
    ];
    const cards = [
      { title: 'Số sinh viên', value: students.length },
      { title: 'Số nhân viên quản lý', value: spsos.length },
    ];
  return (
    <div className="grid grid-cols-[16rem_auto]">
      {/* Sidebar and Topbar */}
      <SideBar/>
      <TopBar></TopBar>
      {/* Main page  */}
      <div className="m-4 pt-20 pl-2 w-auto">
        <SummaryCards cards_prob={cards} />
        <div className="grid grid-cols-2 gap-4 mt-4">
          <StudentList students_prob={students} />
          <SpsoList spsos_prob={spsos}/>
        </div>
      </div>
    </div>
  );
}