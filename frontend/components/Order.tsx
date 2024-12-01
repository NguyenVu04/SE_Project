'use client';
import React from 'react'
import { useState } from 'react'
interface OrderProps {
        studentId: string;
    }
type Order = {
  id: string;
  studentId: string;
  printerId: string;
  documentId: string;
  paperSize: string;
  pageNumbers: number[];
  numberOfCopies: number;
  singleSided: boolean;
  timeOrdered: string;
  done: boolean;
  cost: number;
};
const Order: React.FC<OrderProps> = ({ studentId }) => {    
    const orders = [
  {
    "id": "O001",
    "studentId": "1",
    "printerId": "P001",
    "documentId": "D001",
    "paperSize": "A4",
    "pageNumbers": [1, 2, 3],
    "numberOfCopies": 2,
    "singleSided": true,
    "timeOrdered": "2024-12-01T08:30:00.000Z",
    "done": true,
    "cost": 50
  },
  {
    "id": "O002",
    "studentId": "1",
    "printerId": "P002",
    "documentId": "D002",
    "paperSize": "A3",
    "pageNumbers": [1, 2],
    "numberOfCopies": 1,
    "singleSided": false,
    "timeOrdered": "2024-12-01T09:00:00.000Z",
    "done": false,
    "cost": 30
  },
  {
    "id": "O003",
    "studentId": "1",
    "printerId": "P003",
    "documentId": "D003",
    "paperSize": "Letter",
    "pageNumbers": [1],
    "numberOfCopies": 3,
    "singleSided": true,
    "timeOrdered": "2024-12-01T10:00:00.000Z",
    "done": true,
    "cost": 45
  },
  {
    "id": "O004",
    "studentId": "1",
    "printerId": "P001",
    "documentId": "D004",
    "paperSize": "Legal",
    "pageNumbers": [1, 2, 3, 4],
    "numberOfCopies": 1,
    "singleSided": false,
    "timeOrdered": "2024-12-01T11:00:00.000Z",
    "done": true,
    "cost": 60
  },
  {
    "id": "O005",
    "studentId": "1",
    "printerId": "P004",
    "documentId": "D005",
    "paperSize": "A4",
    "pageNumbers": [1, 2, 3, 4, 5],
    "numberOfCopies": 2,
    "singleSided": true,
    "timeOrdered": "2024-12-01T12:00:00.000Z",
    "done": false,
    "cost": 75
  },
  {
    "id": "O006",
    "studentId": "S12350",
    "printerId": "P005",
    "documentId": "D006",
    "paperSize": "A4",
    "pageNumbers": [1],
    "numberOfCopies": 1,
    "singleSided": true,
    "timeOrdered": "2024-12-01T13:00:00.000Z",
    "done": true,
    "cost": 20
  },
  {
    "id": "O007",
    "studentId": "S12351",
    "printerId": "P006",
    "documentId": "D007",
    "paperSize": "A3",
    "pageNumbers": [1, 2, 3],
    "numberOfCopies": 3,
    "singleSided": false,
    "timeOrdered": "2024-12-01T14:00:00.000Z",
    "done": true,
    "cost": 90
  },
  {
    "id": "O008",
    "studentId": "S12352",
    "printerId": "P007",
    "documentId": "D008",
    "paperSize": "Letter",
    "pageNumbers": [1, 2],
    "numberOfCopies": 2,
    "singleSided": true,
    "timeOrdered": "2024-12-01T15:00:00.000Z",
    "done": false,
    "cost": 40
  },
  {
    "id": "O009",
    "studentId": "S12353",
    "printerId": "P008",
    "documentId": "D009",
    "paperSize": "Legal",
    "pageNumbers": [1, 2, 3, 4, 5],
    "numberOfCopies": 1,
    "singleSided": false,
    "timeOrdered": "2024-12-01T16:00:00.000Z",
    "done": true,
    "cost": 100
  },
  {
    "id": "O010",
    "studentId": "S12354",
    "printerId": "P009",
    "documentId": "D010",
    "paperSize": "A4",
    "pageNumbers": [1, 2],
    "numberOfCopies": 1,
    "singleSided": true,
    "timeOrdered": "2024-12-01T17:00:00.000Z",
    "done": false,
    "cost": 25
  }
    ];
    const [orderList, setOrderList] = useState(orders);
    
    const filteredOrders = orderList.filter(order => order.studentId === studentId);

    const handleDelete = (id: string) => {
        const updatedOrders = orderList.filter((order) => order.id !== id);
        setOrderList(updatedOrders);
    };
    return (
        <div
            style={{ position: 'absolute', top: 70, left: 150, right: 150, bottom: 30 }}
            className="bg-white flex flex-col items-center py-5 px-20" // Sử dụng justify-center để căn giữa
        >
            <h1 className='text-3xl'>Danh sách hóa đơn</h1>
            <h1>Mã sinh viên {studentId}</h1>
            <hr className="border-b border-black w-full mt-2" />
            {/* cards container */}
            <div>
                <div className="overflow-auto mt-4">
                    <table className="min-w-full bg-white border border-gray-300">
                        <thead>
                        <tr className="bg-hcmut-dark text-white uppercase text-sm leading-normal">
                            <th className="py-2 px-3 text-left">Order ID</th>
                            <th className="py-2 px-3 text-left">Student ID</th>
                            <th className="py-2 px-3 text-left">Printer ID</th>
                            <th className="py-2 px-3 text-left">Document ID</th>
                            <th className="py-2 px-3 text-left">Paper Size</th>
                            <th className="py-2 px-3 text-left">Pages</th>
                            <th className="py-2 px-3 text-left">Copies</th>
                            <th className="py-2 px-3 text-left">Single-Sided</th>
                            <th className="py-2 px-3 text-left">Time Ordered</th>
                            <th className="py-2 px-3 text-left">Done</th>
                            <th className="py-2 px-3 text-left">Cost</th>
                            <th className="py-2 px-3 text-left">Actions</th>
                        </tr>
                        </thead>
                        <tbody className="text-gray-600 text-sm">
                        {filteredOrders.map((order) => (
                            <tr
                            key={order.id}
                            className="border-b border-gray-200 hover:bg-gray-100"
                            >
                            <td className="py-2 px-3">{order.id}</td>
                            <td className="py-2 px-3">{order.studentId}</td>
                            <td className="py-2 px-3">{order.printerId}</td>
                            <td className="py-2 px-3">{order.documentId}</td>
                            <td className="py-2 px-3">{order.paperSize}</td>
                            <td className="py-2 px-3">
                                {order.pageNumbers.join(", ")}
                            </td>
                            <td className="py-2 px-3">{order.numberOfCopies}</td>
                            <td className="py-2 px-3">
                                {order.singleSided ? "Yes" : "No"}
                            </td>
                            <td className="py-2 px-3">
                                {new Date(order.timeOrdered).toLocaleString()}
                            </td>
                            <td className="py-2 px-3">{order.done ? "Yes" : "No"}</td>
                            <td className="py-2 px-3">${order.cost}</td>
                            <td className="py-2 px-3">
                                <button
                                onClick={() => handleDelete(order.id)}
                                className="bg-red-500 text-white py-1 px-2 rounded hover:bg-red-600"
                                >
                                Delete
                                </button>
                            </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                    {orderList.length === 0 && (
                        <div className="text-center py-4">No orders available</div>
                    )}
                </div>
            </div>
        </div>
    )
}

export default Order