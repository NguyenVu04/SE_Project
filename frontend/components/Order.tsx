'use client';
import deleteOrder from '@/lib/delete-order';
import getStudentOrders from '@/lib/get-student-orders';
import React, { useEffect } from 'react'
import { useState } from 'react'
interface OrderProps {
    studentId: string;
}
export type StudentOrder = {
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
    const [orderList, setOrderList] = useState<StudentOrder[]>([]);

    useEffect(() => {
        getStudentOrders(studentId)
            .then((orders) => {
                setOrderList(orders);
            })
            .catch(() => {
                alert('Có lỗi xảy ra! Vui lòng thử lại')
            })
    }, [studentId]);

    return (
        <div
            className="bg-white flex flex-col items-center py-5 px-20 absolute top-[70px] left-[150px] right-[150px] bottom-[30px]"
        >
            <h1 className='text-3xl uppercase'>Danh sách hóa đơn</h1>
            <hr className="border-b border-black w-full mt-2" />
            {/* cards container */}
            <div>
                <div className="overflow-auto mt-4">
                    <table className="min-w-full bg-white border border-gray-300">
                        <thead>
                            <tr className="bg-hcmut-dark text-white uppercase text-sm leading-normal">
                                <th className="py-2 px-3 text-left">Tên tài liệu</th>
                                <th className="py-2 px-3 text-left">Cỡ giấy</th>
                                <th className="py-2 px-3 text-left">Trang in</th>
                                <th className="py-2 px-3 text-left">Số bản</th>
                                <th className="py-2 px-3 text-left">In hai mặt</th>
                                <th className="py-2 px-3 text-left">Thời gian đặt</th>
                                <th className="py-2 px-3 text-left">Tình trạng</th>
                                <th className="py-2 px-3 text-left">Giá (VNĐ)</th>
                                <th className="py-2 px-3 text-left">Hành động</th>
                            </tr>
                        </thead>
                        <tbody className="text-gray-600 text-sm">
                            {orderList.map((order) => (
                                <tr
                                    key={order.id}
                                    className="border-b border-gray-200 hover:bg-gray-100"
                                >
                                    <td className="py-2 px-3">{order.documentId}</td>
                                    <td className="py-2 px-3">{order.paperSize}</td>
                                    <td className="py-2 px-3">
                                        {order.pageNumbers.join(", ")}
                                    </td>
                                    <td className="py-2 px-3">{order.numberOfCopies}</td>
                                    <td className="py-2 px-3">
                                        {order.singleSided ? "Không" : "Có"}
                                    </td>
                                    <td className="py-2 px-3">
                                        {new Date(order.timeOrdered).toLocaleString()}
                                    </td>
                                    <td className="py-2 px-3">{order.done ? "Hoàn thành" : "Chưa hoàn thành"}</td>
                                    <td className="py-2 px-3">{order.cost * 500}</td>
                                    <td className="py-2 px-3">
                                        <button
                                            type='button'
                                            onClick={async () => {
                                                const status = await deleteOrder(order.id);
                                                if (status !== 200) {
                                                    alert('Có lỗi xảy ra! Vui lòng thử lại...')
                                                    return;
                                                }
                                                getStudentOrders(studentId)
                                                    .then((orders) => {
                                                        setOrderList(orders);
                                                    })
                                                    .catch(() => {
                                                        alert('Có lỗi xảy ra! Vui số lịch thử lại....')
                                                    })
                                            }}
                                            disabled={!order.done}
                                            className={`text-white py-1 px-2 rounded ${!order.done ? "disabled:bg-slate-500" : "bg-red-500 hover:bg-red-600"}`}
                                        >
                                            Xóa
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