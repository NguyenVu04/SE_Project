'use client';
import React, { useEffect, useState } from "react";
import { redirect, useSearchParams } from "next/navigation";
import TopBar from "@/components/ui/topbar";
import Link from "next/link";
import getPrinterOrder, { Order } from "@/lib/printer-order";
import updateOrderStatus from "@/lib/update-order-status";
import getUserId from "@/lib/user-id";
import Image from "next/image";

export default function OrderPage() {
  const [id, setId] = useState<string | null>(null);

  useEffect(() => {
    getUserId("spso")
      .then((id) => {
        setId(id);
      })
      .catch(() => {
        redirect("/");
      });
  }, []);

  const params = useSearchParams();
  const printerId = params?.get("printerId");

  if (!printerId) {
    redirect("/spso/printer");
  }

  const [orders, setOrders] = useState<Order[]>([]);

  useEffect(() => {
    getPrinterOrder(printerId)
      .then(setOrders)
      .catch(() => {
        alert("Có lỗi xảy ra! Vui lòng thử lại");
      });
  }, [printerId]);

  if (!id) {
    return (
      <div className="flex w-screen h-screen justify-center items-center">
        <Image src="/hcmut.svg" alt="HCMUT" width={64} height={64} />
      </div>
    )
  }

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
          {orders.map((order) => (
            <div key={order.orderId} className="border border-gray-300 p-4 rounded-lg bg-orange-500">
              <p><strong>Cỡ giấy:</strong> {order.paperSize}</p>
              <p><strong>Các trang cần in:</strong> {order.pageNumbers.join(', ')}</p>
              <p><strong>Số bản in:</strong> {order.numberOfCopies}</p>
              <p><strong>In hai mặt:</strong> {order.singleSided ? 'Không' : 'Có'}</p>
              <div className="w-full grid-cols-3 space-x-4 mt-4">
                <Link href={order.documentUrl} target="_blank">
                  <button onClick={() => {
                    order.timeReceived = new Date().toISOString();
                  }} type="button" className="bg-blue-800 text-white p-2 rounded-md">Tải tài liệu</button>
                </Link>
                <button type="button" className="bg-green-800 text-white p-2 rounded-md"
                  onClick={async () => {
                    const status = await updateOrderStatus(order.orderId, true, order.timeReceived ?? new Date().toISOString(), new Date().toISOString());
                    if (status === 200) {
                      getPrinterOrder(printerId)
                        .then(setOrders)
                        .catch(() => {
                          alert("Có lỗi xảy ra! Vui lòng thử lại");
                        });
                    } else {
                      alert("Có lỗi xảy ra! Vui lòng thử lại");
                    }
                  }}>Hoàn thành</button>
                <button type="button" className="bg-red-800 text-white p-2 rounded-md"
                  onClick={async () => {
                    const status = await updateOrderStatus(order.orderId, false, order.timeReceived ?? new Date().toISOString(), new Date().toISOString());
                    if (status === 200) {
                      getPrinterOrder(printerId)
                        .then(setOrders)
                        .catch(() => {
                          alert("Có lỗi xảy ra! Vui lòng thử lại");
                        });
                    } else {
                      alert("Có lỗi xảy ra! Vui lòng thử lại");
                    }
                  }}>Thất bại</button>
              </div>
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