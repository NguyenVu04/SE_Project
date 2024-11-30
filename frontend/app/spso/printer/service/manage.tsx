'use client';
import React from "react";
import TopBar from "../../../../components/ui/topbar";
import { useEffect, useState } from "react";
import setPrinterStatus from "@/lib/printer-status";
import { redirect } from "next/navigation";
import getAllPrinters from "@/lib/get-printer";


interface ServicePageProps {
  onAddPrinterClick: () => void;
}

export type Printer = {
  "id": string,
  "manufacturer": string,
  "model": string,
  "description": string,
  "campusName": string,
  "buildingName": string,
  "roomNumber": string,
  "active": boolean
}

export default function ServicePage({ onAddPrinterClick }: ServicePageProps) {
  const [printers, setPrinters] = useState<Printer[]>([]);

  useEffect(() => {
    getAllPrinters().then((printers) => {
      setPrinters(printers);
    }).catch((error) => {
      alert(error);
    })
  }, []);

  return (
    <div className="flex flex-col min-h-screen">
      <TopBar></TopBar>
      {/* main content */}
      <div
        id="main-content"
        className="flex-grow flex flex-col items-center justify-center text-black bg-gradient-to-b from-[#0381FF] to-[#02067A]"
      >
        {/* manage printer */}
        <div className="bg-white p-6 rounded-lg shadow-lg max-w-5xl w-full mt-3 mb-3">
          <div className="flex justify-between items-center mb-6">
            <h1 className="text-xl font-bold">QUẢN LÝ MÁY IN</h1>
            <button type="button" className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600" onClick={onAddPrinterClick}>
              Thêm máy in
            </button>
          </div>
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            {printers.map((printer) => (
              <div
                key={printer.id}
                className={`border rounded-lg p-4 ${printer.active
                  ? "border-green-500"
                  : "border-red-500"
                  }`}
              >
                <p><strong>Nhà sản xuất:</strong> {printer.manufacturer}</p>
                <p><strong>Mẫu máy in:</strong> {printer.model}</p>
                <p><strong>Tên trường:</strong> {printer.campusName}</p>
                <p><strong>Tên tòa:</strong> {printer.buildingName}</p>
                <p><strong>Số phòng:</strong> {printer.roomNumber}</p>
                <div className="mt-4">
                  <button
                    type="button"
                    className={`py-2 px-4 rounded ${!printer.active
                      ? "bg-green-500 text-white"
                      : "bg-red-500 text-white"
                      }`}
                    onClick={async () => {
                      printer.active = !printer.active;
                      setPrinters([...printers]);
                      const result = await setPrinterStatus(printer.active, printer.id);
                      if (!result) {
                        alert("Có lỗi xây ra! Vui lòng thử lại.");
                        return;
                      }
                    }}
                  >
                    {!printer.active ? "Kích hoạt" : "Vô hiệu"}
                  </button>
                </div>
              </div>
            ))}
          </div>
          <div className="mt-6 text-center">
            <button onClick={() => redirect("/spso")} type="button" className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600">
              Hoàn thành
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}