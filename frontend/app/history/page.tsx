'use client';
import { Button } from "@/components/ui/button";
import StudentPopUp from "./popupstudent";
import PrinterPopUp from "./popupprinter";
import { PiStudent } from "react-icons/pi";
import { TfiPrinter } from "react-icons/tfi";
import { FaListUl } from "react-icons/fa";
import { useState } from "react";
export default function Home() {
    const [studentId, setStudentId] = useState("null");
    const [isStudentFormOpen, setStudentFormOpen] = useState(false);
    const [printerId, setPrinterId] = useState("null");
    const [isPrinterFormOpen, setPrinterFormOpen] = useState(false);
    function toggleStudentForm() {
        console.log(isStudentFormOpen?"Student form open":"Student form close");
        setStudentFormOpen(!isStudentFormOpen);
        
    }
    function togglePrinterForm() {
        console.log(isPrinterFormOpen?"Printer form open":"Printer form close");
        setPrinterFormOpen(!isPrinterFormOpen);
    }
    const studentHistory = [
    {
        "id": "1",
        "studentId": "2213994",
        "printerId": "P001",
        "documentId": "D001",
        "paperSize": "A4",
        "pageNumbers": [1, 2, 3],
        "numberOfCopies": 2,
        "singleSided": true,
        "timeOrdered": "2024-11-30T08:30:00.000Z",
        "timeReceived": "2024-11-30T08:31:00.000Z",
        "timePrinted": "2024-11-30T08:35:00.000Z",
        "successful": true
    },
    {
        "id": "2",
        "studentId": "2213994",
        "printerId": "P002",
        "documentId": "D002",
        "paperSize": "A3",
        "pageNumbers": [1, 2],
        "numberOfCopies": 1,
        "singleSided": false,
        "timeOrdered": "2024-11-30T09:00:00.000Z",
        "timeReceived": "2024-11-30T09:02:00.000Z",
        "timePrinted": "2024-11-30T09:10:00.000Z",
        "successful": false
    },
    {
        "id": "3",
        "studentId": "S12347",
        "printerId": "P003",
        "documentId": "D003",
        "paperSize": "Letter",
        "pageNumbers": [1],
        "numberOfCopies": 3,
        "singleSided": true,
        "timeOrdered": "2024-11-30T10:00:00.000Z",
        "timeReceived": "2024-11-30T10:01:00.000Z",
        "timePrinted": "2024-11-30T10:05:00.000Z",
        "successful": true
    },
    {
        "id": "4",
        "studentId": "S12348",
        "printerId": "P001",
        "documentId": "D004",
        "paperSize": "Legal",
        "pageNumbers": [1, 2, 3, 4],
        "numberOfCopies": 1,
        "singleSided": false,
        "timeOrdered": "2024-11-30T11:00:00.000Z",
        "timeReceived": "2024-11-30T11:02:00.000Z",
        "timePrinted": "2024-11-30T11:15:00.000Z",
        "successful": true
    },
    {
        "id": "5",
        "studentId": "S12349",
        "printerId": "P004",
        "documentId": "D005",
        "paperSize": "A4",
        "pageNumbers": [1, 2, 3, 4, 5],
        "numberOfCopies": 2,
        "singleSided": true,
        "timeOrdered": "2024-11-30T12:00:00.000Z",
        "timeReceived": "2024-11-30T12:01:00.000Z",
        "timePrinted": "2024-11-30T12:10:00.000Z",
        "successful": false
    }
    ];
    const printerHistory = [
        {
            "id": "P1001-01",
            "studentId": "S2001",
            "printerId": "P1001",
            "documentId": "D1001",
            "paperSize": "A4",
            "pageNumbers": [1, 2, 3],
            "numberOfCopies": 2,
            "singleSided": true,
            "timeOrdered": "2024-11-30T08:00:00.000Z",
            "timeReceived": "2024-11-30T08:01:00.000Z",
            "timePrinted": "2024-11-30T08:05:00.000Z",
            "successful": true
        },
        {
            "id": "P1001-02",
            "studentId": "S2002",
            "printerId": "P1001",
            "documentId": "D1002",
            "paperSize": "A3",
            "pageNumbers": [1, 2],
            "numberOfCopies": 1,
            "singleSided": false,
            "timeOrdered": "2024-11-30T09:00:00.000Z",
            "timeReceived": "2024-11-30T09:01:00.000Z",
            "timePrinted": "2024-11-30T09:10:00.000Z",
            "successful": false
        },
        {
            "id": "P1001-03",
            "studentId": "S2003",
            "printerId": "P1001",
            "documentId": "D1003",
            "paperSize": "Letter",
            "pageNumbers": [1],
            "numberOfCopies": 3,
            "singleSided": true,
            "timeOrdered": "2024-11-30T10:00:00.000Z",
            "timeReceived": "2024-11-30T10:01:00.000Z",
            "timePrinted": "2024-11-30T10:05:00.000Z",
            "successful": true
        },
        {
            "id": "P1001-04",
            "studentId": "S2004",
            "printerId": "P1001",
            "documentId": "D1004",
            "paperSize": "Legal",
            "pageNumbers": [1, 2, 3, 4],
            "numberOfCopies": 1,
            "singleSided": false,
            "timeOrdered": "2024-11-30T11:00:00.000Z",
            "timeReceived": "2024-11-30T11:02:00.000Z",
            "timePrinted": "2024-11-30T11:15:00.000Z",
            "successful": true
        },
        {
            "id": "P1001-05",
            "studentId": "S2005",
            "printerId": "P1001",
            "documentId": "D1005",
            "paperSize": "A4",
            "pageNumbers": [1, 2, 3, 4, 5],
            "numberOfCopies": 2,
            "singleSided": true,
            "timeOrdered": "2024-11-30T12:00:00.000Z",
            "timeReceived": "2024-11-30T12:01:00.000Z",
            "timePrinted": "2024-11-30T12:10:00.000Z",
            "successful": false
        }
    ];
    const filteredHistoryStudent = studentHistory.filter(
    (history) => history.studentId === studentId
    );
    const filteredHistoryPrinter = printerHistory.filter(
        (history) => history.printerId === printerId
    );

    return (
        <div className="grid grid-cols-[16rem_auto] h-screen">
        {/* Sidebar */}
        <div className="w-64 border-solid border-2 border-gray-500 shadow-md grid grid-rows-4">
            <div className="flex items-center justify-center text-xl">Printing History Management</div>
            {/* Menu Items */}
            <div className="flex flex-col gap-6 w-full">
                {/* Dashboard */}
                <button 
                    className="flex items-center gap-4 cursor-pointer p-3 rounded-md  text-gray-700
                    hover:bg-gray-700 hover:text-white transition-colors duration-200"
                    onClick={toggleStudentForm}>
                <PiStudent size={24}/>
                <p className="text-sm font-medium ">Lịch sử in của sinh viên</p>
                </button>

                {/* Print History */}
                <div 
                    className="flex items-center gap-4 cursor-pointer p-3 rounded-md  text-gray-700
                    hover:bg-gray-700 hover:text-white transition-colors duration-200"
                    onClick={togglePrinterForm}>
                <TfiPrinter size={24}/>
                <p className="text-sm font-medium ">Lịch sử in của máy in</p>
                </div>

                {/* Printer List */}
                <div 
                    className="flex items-center gap-4 cursor-pointer p-3 rounded-md  text-gray-700
                    hover:bg-gray-700 hover:text-white transition-colors duration-200">
                <FaListUl size={24}/>
                <p className="text-sm font-medium ">Lịch sử lệnh in</p>
                </div>
            </div>
        </div>
        {/* Main content */}
            <div className="border-solid border-2 border-gray-500 shadow-md p-2 grid grid-rows-[4rem_auto]">
                <div className="flex justify-between border-b">
                    <div>History here</div>
                    <Button type='button' onClick={() => {
                        setStudentId('null');
                        setPrinterId('null');
                    }}>Clear</Button>
                </div>
                {/* History part */}
                <div>
                    {isStudentFormOpen && (
                    <StudentPopUp 
                        value={studentId}
                        toggleStudentForm={toggleStudentForm} 
                        onSubmitStudentId={setStudentId}
                    />
                    )}
                    {/* render list of history of a student */}
                    <div>
                        <h2>Printing History for Student {studentId}</h2>
                        {/* history of student containter */}
                        {filteredHistoryStudent.length === 0 ? (
                            <p>No history found for this student.</p>
                        ) : (
                            <ul className="grid grid-cols-3">
                            {filteredHistoryStudent.map((history) => (
                                <li key={history.id} className="border py-2 rounded-xl m-2 p-2">
                                <strong>Document ID:</strong> {history.documentId} <br />
                                <strong>Printer ID:</strong> {history.printerId} <br />
                                <strong>Paper Size:</strong> {history.paperSize} <br />
                                <strong>Page Numbers:</strong> {history.pageNumbers.join(", ")} <br />
                                <strong>Copies:</strong> {history.numberOfCopies} <br />
                                <strong>Single-sided:</strong> {history.singleSided ? "Yes" : "No"} <br />
                                <strong>Ordered At:</strong> {new Date(history.timeOrdered).toLocaleString()} <br />
                                <strong>Received At:</strong> {new Date(history.timeReceived).toLocaleString()} <br />
                                <strong>Printed At:</strong> {new Date(history.timePrinted).toLocaleString()} <br />
                                <strong>Status:</strong> {history.successful ? "Successful" : "Failed"}
                                </li>
                            ))}
                            </ul>
                        )}
                    </div>
                    {isPrinterFormOpen && (
                        <PrinterPopUp 
                            value={printerId}
                            togglePrinterForm={togglePrinterForm}
                            onSubmitPrinterId={setPrinterId}
                        />
                    )}
                    {/* render list of history of a printer */}
                    <div>
                        <h2> Printing history for printer {printerId}</h2>
                        {filteredHistoryPrinter.length === 0 ? (
                            <p>No history found for this printer.</p>
                        ) : (
                            <ul className="grid grid-cols-3">
                            {filteredHistoryPrinter.map((history) => (
                                <li key={history.id} className="border py-2 rounded-xl m-2 p-2">
                                <strong>Document ID:</strong> {history.documentId} <br />
                                <strong>Printer ID:</strong> {history.printerId} <br />
                                <strong>Paper Size:</strong> {history.paperSize} <br />
                                <strong>Page Numbers:</strong> {history.pageNumbers.join(", ")} <br />
                                <strong>Copies:</strong> {history.numberOfCopies} <br />
                                <strong>Single-sided:</strong> {history.singleSided ? "Yes" : "No"} <br />
                                <strong>Ordered At:</strong> {new Date(history.timeOrdered).toLocaleString()} <br />
                                <strong>Received At:</strong> {new Date(history.timeReceived).toLocaleString()} <br />
                                <strong>Printed At:</strong> {new Date(history.timePrinted).toLocaleString()} <br />
                                <strong>Status:</strong> {history.successful ? "Successful" : "Failed"}
                                </li>
                            ))}
                            </ul>
                        )}
                    </div>
                </div>
            </div>
        </div>
  );
}
