'use client';
import React, { useEffect, useState } from "react";
import { useRouter, useSearchParams } from "next/navigation";
import TopBar from "@/components/ui/topbar";
import Link from "next/link";
type Order = {
    id: string;
    studentId: string;
    printerId: string;
    documentId: string;
    paperSize: string;
    pageNumbers: number[];
    numberOfCopies: number;
    singleSided: boolean;
    timeOrdered: string; // ISO format string
    done: boolean;
    cost: number;
};

export default function OrderPage() {

    const router = useRouter();
    const [orders, setOrders] = useState<Order[]>([]);
    const temorders: Order[] = [
  {
    id: "P001",
    studentId: "S12345",
    printerId: "P001",
    documentId: "D001",
    paperSize: "A4",
    pageNumbers: [1, 2, 3],
    numberOfCopies: 2,
    singleSided: true,
    timeOrdered: "2024-11-30T08:30:00.000Z",
    done: true,
    cost: 50,
  },
  {
    id: "P001",
    studentId: "S12346",
    printerId: "P002",
    documentId: "D002",
    paperSize: "A3",
    pageNumbers: [1, 2],
    numberOfCopies: 1,
    singleSided: false,
    timeOrdered: "2024-11-30T09:00:00.000Z",
    done: false,
    cost: 30,
  },
  {
    id: "P001",
    studentId: "S12347",
    printerId: "P003",
    documentId: "D003",
    paperSize: "Letter",
    pageNumbers: [1],
    numberOfCopies: 3,
    singleSided: true,
    timeOrdered: "2024-11-30T10:00:00.000Z",
    done: true,
    cost: 45,
  },
  {
    id: "P001",
    studentId: "S12348",
    printerId: "P001",
    documentId: "D004",
    paperSize: "Legal",
    pageNumbers: [1, 2, 3, 4],
    numberOfCopies: 1,
    singleSided: false,
    timeOrdered: "2024-11-30T11:00:00.000Z",
    done: true,
    cost: 60,
  },
  {
    id: "P002",
    studentId: "S12349",
    printerId: "P004",
    documentId: "D005",
    paperSize: "A4",
    pageNumbers: [1, 2, 3, 4, 5],
    numberOfCopies: 2,
    singleSided: true,
    timeOrdered: "2024-11-30T12:00:00.000Z",
    done: false,
    cost: 75,
  },
  {
    id: "P002",
    studentId: "S12350",
    printerId: "P005",
    documentId: "D006",
    paperSize: "A4",
    pageNumbers: [1],
    numberOfCopies: 1,
    singleSided: true,
    timeOrdered: "2024-11-30T13:00:00.000Z",
    done: true,
    cost: 20,
  },
  {
    id: "P002",
    studentId: "S12351",
    printerId: "P006",
    documentId: "D007",
    paperSize: "A3",
    pageNumbers: [1, 2, 3],
    numberOfCopies: 3,
    singleSided: false,
    timeOrdered: "2024-11-30T14:00:00.000Z",
    done: true,
    cost: 90,
  },
  {
    id: "P002",
    studentId: "S12352",
    printerId: "P007",
    documentId: "D008",
    paperSize: "Letter",
    pageNumbers: [1, 2],
    numberOfCopies: 2,
    singleSided: true,
    timeOrdered: "2024-11-30T15:00:00.000Z",
    done: false,
    cost: 40,
  },
  {
    id: "P002",
    studentId: "S12353",
    printerId: "P008",
    documentId: "D009",
    paperSize: "Legal",
    pageNumbers: [1, 2, 3, 4, 5],
    numberOfCopies: 1,
    singleSided: false,
    timeOrdered: "2024-11-30T16:00:00.000Z",
    done: true,
    cost: 100,
  },
  {
    id: "O010",
    studentId: "S12354",
    printerId: "P009",
    documentId: "D010",
    paperSize: "A4",
    pageNumbers: [1, 2],
    numberOfCopies: 1,
    singleSided: true,
    timeOrdered: "2024-11-30T17:00:00.000Z",
    done: false,
    cost: 25,
  },
    ];
    // Use useEffect to set initial state
    // Fix here DUY VU: 
    useEffect(() => {
        setOrders(temorders);
    }, []); // Empty dependency array ensures it runs only once

    const searchParams = useSearchParams();
    const printerId = searchParams.get("printerId");
    const filteredOrders = temorders.filter(
        (order) => order.printerId === printerId
    );
    return (
        <div>
            <TopBar></TopBar>
            {/* main content */}
            <div className="min-h-screen grid text-black bg-gradient-to-b from-[#0381FF] to-[#02067A]">
                <div className="text-center">
                    <h1 className="text-orange-500 text-6xl mt-5">Order Management</h1>
                    <p className="text-xl text-white mt-5">Đơn hàng của máy in: {printerId}</p>  
                </div>
                <div className="grid grid-cols-4 gap-4">
                {filteredOrders.map((order) => (
                    <div key={order.id} className="border border-gray-300 p-4 mb-4 rounded-lg bg-orange-500">
                        <h2 className="font-bold text-lg">Order ID: {order.id}</h2>
                        <p><strong>Student ID:</strong> {order.studentId}</p>
                        <p><strong>Printer ID:</strong> {order.printerId}</p>
                        <p><strong>Document ID:</strong> {order.documentId}</p>
                        <p><strong>Paper Size:</strong> {order.paperSize}</p>
                        <p><strong>Page Numbers:</strong> {order.pageNumbers.join(', ')}</p>
                        <p><strong>Copies:</strong> {order.numberOfCopies}</p>
                        <p><strong>Single Sided:</strong> {order.singleSided ? 'Yes' : 'No'}</p>
                        <p><strong>Time Ordered:</strong> {new Date(order.timeOrdered).toLocaleString()}</p>
                        <p><strong>Status:</strong> {order.done ? 'Completed' : 'Pending'}</p>
                        <p><strong>Cost:</strong> ${order.cost}</p>
                    </div>
                ))}
                </div>
                <Link href={"/spso/printer"} className="text-center"> 
                        <button className="mt-4 px-4 py-2 bg-orange-500 text-white rounded">Quay lại</button> 
                </Link>
            </div>
        </div>
    );
}