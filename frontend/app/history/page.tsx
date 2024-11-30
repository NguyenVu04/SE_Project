'use client';
import { Button } from "@/components/ui/button";
import StudentPopUp from "./popupstudent";
import PrinterPopUp from "./popupprinter";
import { PiStudent } from "react-icons/pi";
import { TfiPrinter } from "react-icons/tfi";
// import { FaListUl } from "react-icons/fa";
import { useEffect, useState } from "react";
import getStudentHistory from "@/lib/get-student-history";
import getPrinterHistory from "@/lib/get-printer-history";

export type HistoryItem = {
    id: string;
    studentId: string;
    printerId: string;
    documentId: string;
    paperSize: string;
    pageNumbers: number[];
    numberOfCopies: number;
    singleSided: boolean;
    timeOrdered: string;
    timeReceived: string;
    timePrinted: string;
    successful: boolean;
}

export default function Home() {
    const [studentId, setStudentId] = useState<string>("");
    const [isStudentFormOpen, setStudentFormOpen] = useState(false);
    const [printerId, setPrinterId] = useState<string>("");
    const [isPrinterFormOpen, setPrinterFormOpen] = useState(false);
    const [printerHistoryFrom, setPrinterHistoryFrom] = useState<string>("");
    const [printerHistoryTo, setPrinterHistoryTo] = useState<string>("");
    function toggleStudentForm() {
        setStudentFormOpen(!isStudentFormOpen);

    }
    function togglePrinterForm() {
        setPrinterFormOpen(!isPrinterFormOpen);
    }

    const [studentHistory, setStudentHistory] = useState<HistoryItem[]>([]);
    const [printerHistory, setPrinterHistory] = useState<HistoryItem[]>([]);

    useEffect(() => {
        if (!studentId) {
            return;
        }

        getStudentHistory(studentId)
            .then((history) => {
                setStudentHistory(history);
            })
            .catch(() => {
                alert("Error fetching student history: " + studentId);
            });
    }, [studentId]);

    useEffect(() => {
        if (!printerId || !printerHistoryFrom || !printerHistoryTo) {
            return;
        }

        getPrinterHistory(printerId, printerHistoryFrom, printerHistoryTo)
            .then((history) => {
                setPrinterHistory(history);
            })
            .catch(() => {
                alert("Error fetching printer history: " + printerId);
            });
    }, [printerId, printerHistoryFrom, printerHistoryTo]);

    return (
        <div className="grid grid-cols-[16rem_auto] h-screen">
            {/* Sidebar */}
            <div className="w-64 border-solid border-2 border-gray-500 shadow-md grid grid-rows-4">
                <div className="flex items-center justify-center text-xl uppercase font-bold">quản lý lịch sử in</div>
                {/* Menu Items */}
                <div className="flex flex-col gap-6 w-full">
                    {/* Dashboard */}
                    <button
                        type="button"
                        className="flex items-center gap-4 cursor-pointer p-3 rounded-md  text-gray-700
                    hover:bg-gray-700 hover:text-white transition-colors duration-200"
                        onClick={toggleStudentForm}>
                        <PiStudent size={24} />
                        <p className="text-sm font-medium ">Lịch sử in của sinh viên</p>
                    </button>

                    {/* Print History */}
                    <div
                        className="flex items-center gap-4 cursor-pointer p-3 rounded-md  text-gray-700
                    hover:bg-gray-700 hover:text-white transition-colors duration-200"
                        onClick={togglePrinterForm}>
                        <TfiPrinter size={24} />
                        <p className="text-sm font-medium ">Lịch sử in của máy in</p>
                    </div>

                    {/* Printer List */}
                    {/* <div
                        className="flex items-center gap-4 cursor-pointer p-3 rounded-md  text-gray-700
                    hover:bg-gray-700 hover:text-white transition-colors duration-200">
                        <FaListUl size={24} />
                        <p className="text-sm font-medium ">Lịch sử lệnh in</p>
                    </div> */}
                </div>
            </div>
            {/* Main content */}
            <div className="border-solid border-2 border-gray-500 shadow-md p-2 grid grid-rows-[4rem_auto]">
                <div className="flex justify-end border-b">
                    <Button type='button' onClick={() => {
                        setStudentId("");
                        setPrinterId("");
                        setPrinterHistoryFrom("");
                        setPrinterHistoryTo("");
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
                        {studentId && <h2 className="text-xl text-red-500">Lịch sử in của sinh viên: {studentId}</h2>}
                        <ul className="grid grid-cols-3">
                            {studentHistory.map((history) => (
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
                    </div>
                    {isPrinterFormOpen && (
                        <PrinterPopUp
                            value={printerId}
                            togglePrinterForm={togglePrinterForm}
                            onSubmitPrinterId={setPrinterId}
                            onSubmitHistoryFrom={setPrinterHistoryFrom}
                            onSubmitHistoryTo={setPrinterHistoryTo}
                        />
                    )}
                    {/* render list of history of a printer */}
                    <div>
                        {printerId && <h2 className="text-xl text-red-500"> Lịch sử in của máy in: {printerId}</h2>}

                        <ul className="grid grid-cols-3">
                            {printerHistory.map((history) => (
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
                    </div>
                </div>
            </div>
        </div>
    );
}
