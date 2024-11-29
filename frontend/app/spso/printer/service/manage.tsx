'use client';
import React from "react";
import TopBar from "../../../../components/ui/topbar";
import getPrinterOrder, { Order } from "@/lib/printer-order";
import { redirect, useSearchParams } from "next/navigation";
import { useEffect, useState } from "react";

  
interface ServicePageProps {
  onAddPrinterClick: () => void;
}

export default function ServicePage({ onAddPrinterClick }: ServicePageProps) {
    // const [orders, setOrders] = useState<Order[]>([]);
    // const params = useSearchParams();
    // const printerId = params.get("printerId");

    // if (!printerId) {
    //     redirect("/spso/printer");
    // }

    // useEffect(() => {
    //     setInterval(() => {
    //         getPrinterOrder(printerId)
    //             .then(setOrders)
    //             .catch(() => {
    //                 redirect("/spso/printer?error=404");
    //             });
    //     }, 5000);
    // }, [printerId]);
    const printers = [
        { id: 1, status: "active", manufacturer: "HP", model: "OfficeJet Pro 8135e", school: "Đại học Bách Khoa TP.HCM", room: 612 },
        { id: 2, status: "active", manufacturer: "HP", model: "OfficeJet Pro 8135e", school: "Đại học Bách Khoa TP.HCM", room: 812 },
        { id: 3, status: "active", manufacturer: "HP", model: "OfficeJet Pro 8135e", school: "Đại học Bách Khoa TP.HCM", room: 712 },
        { id: 4, status: "inactive", manufacturer: "HP", model: "OfficeJet Pro 8135e", school: "Đại học Bách Khoa TP.HCM", room: 312 },
        { id: 5, status: "inactive", manufacturer: "HP", model: "OfficeJet Pro 8135e", school: "Đại học Bách Khoa TP.HCM", room: 112 },
        { id: 6, status: "inactive", manufacturer: "HP", model: "OfficeJet Pro 8135e", school: "Đại học Bách Khoa TP.HCM", room: 212 },
      ];
    
      return (
        <div className="flex flex-col min-h-screen">
            <TopBar></TopBar>
            {/* main content */}
            <div
                style={{ background: 'linear-gradient(to bottom, #0381FF, #02067A)' }}
                id="main-content"
                className="flex-grow flex flex-col items-center justify-center text-black"
            >
            {/* manage printer */}
            <div className="bg-white p-6 rounded-lg shadow-lg max-w-5xl w-full mt-3 mb-3">
            <div className="flex justify-between items-center mb-6">
              <h1 className="text-xl font-bold">QUẢN LÝ MÁY IN</h1>
              <button className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600" onClick={onAddPrinterClick}>
                Thêm máy in
              </button>
            </div>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              {printers.map((printer) => (
                <div
                  key={printer.id}
                  className={`border rounded-lg p-4 ${
                    printer.status === "active"
                      ? "border-green-500"
                      : "border-red-500"
                  }`}
                >
                  <p><strong>ID:</strong> {printer.id}</p>
                  <p><strong>Nhà sản xuất:</strong> {printer.manufacturer}</p>
                  <p><strong>Mẫu máy in:</strong> {printer.model}</p>
                  <p><strong>Tên trường:</strong> {printer.school}</p>
                  <p><strong>Số phòng:</strong> {printer.room}</p>
                  <div className="mt-4">
                    <button
                      className={`py-2 px-4 rounded ${
                        printer.status === "active"
                          ? "bg-green-500 text-white"
                          : "bg-red-500 text-white"
                      }`}
                    >
                      {printer.status === "active" ? "Kích hoạt" : "Vô hiệu"}
                    </button>
                  </div>
                </div>
              ))}
            </div>
            <div className="mt-6 text-center">
              <button className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600">
                Hoàn thành
              </button>
            </div>
          </div>
            </div>
        </div>
      );
    return (
        <div>
            {/* {
                orders.map((order) => (
                    <div key={order.orderId}>
                        {order.documentUrl}
                    </div>
                ))
            } */}

        </div>
    );
}