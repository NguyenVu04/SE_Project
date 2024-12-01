'use client';
import TopBar from "@/components/ui/topbar";
import addReport from "@/lib/add-report";
import getAllReports from "@/lib/get-all-reports";
import getUserId from "@/lib/user-id";
import Image from "next/image";
import Link from "next/link";
import { redirect } from "next/navigation";
import { useEffect, useState } from "react";

export type Report = {
  filename: string;
  url: string;
  fileSize: number;
  uploadDate: string;
}
export default function ReportPage() {
  const [id, setId] = useState<string | null>(null);
  useEffect(() => {
    getUserId("spso")
      .then((id) => {
        setId(id);
      })
      .catch(() => {
        redirect("/");
      })
  }, []);

  const [reports, setReports] = useState<Report[]>([]);

  useEffect(() => {
    getAllReports()
      .then((reports) => {
        setReports(reports);
      })
      .catch(() => {
        alert('Có lỗi xảy ra! Vui lòng thử lại...')
      })
  }, []);

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [startDate, setStartDate] = useState<string>("");
  const [endDate, setEndDate] = useState<string>("");

  const handleSubmit = async () => {
    if (!startDate || !endDate) {
      alert("Please fill in both dates.");
      return;
    }

    const status = await addReport(startDate, endDate);

    if (status === 200) {
      const reports = await getAllReports();
      if (reports) {
        setReports(reports);
      } else {
        alert('Có lỗi xảy ra! Vui lòng thử lại...');
      }
    } else {
      alert('Có lỗi xảy ra! Vui lòng thử lại...');
    }
    setStartDate("");
    setEndDate("");
    setIsModalOpen(false);
  };

  if (!id) {
    return (
      <div>
        <Image src="/hcmut.svg" alt="hcmut" width={64} height={64} />
      </div>
    )
  }

  return (
    <div>
      <TopBar />
      <div className="bg-gradient-to-b from-[#0381FF] to-[#02067A] w-full">
        <div
          id="main-content"
          className="flex-grow flex flex-col items-center justify-center text-white"
        >
          <h1 className="text-6xl text-white m-5 uppercase">danh sách báo cáo</h1>

        </div>
        {/* Render report */}
        <ul className="grid grid-cols-3 gap-4 mx-4">
          {reports.map((report, index) => (
            <li key={index} className="bg-white h-fit">
              <table className="w-full">
                <tbody>
                  <tr>
                    <td className="font-bold text-nowrap p-2 border-solid border-2 border-black">Tên báo cáo:</td>
                    <td className="text-wrap p-2 border-solid border-2 border-black">{report.filename}</td>
                  </tr>
                  <tr>
                    <td className="font-bold text-nowrap p-2 border-solid border-2 border-black">Kích thước tập tin:</td>
                    <td className="border-solid border-2 p-2 border-black">{report.fileSize} bytes</td>
                  </tr>
                  <tr>
                    <td className="font-bold text-nowrap p-2 border-solid border-2 border-black">Ngày tạo:</td>
                    <td className="border-solid border-2 p-2 border-black">{new Date(report.uploadDate).toLocaleString()}</td>
                  </tr>
                </tbody>
              </table>
              <Link href={report.url} target="_blank">
                <button type="button" className="w-full bg-green-800 hover:bg-green-600 text-white py-2">Tải báo cáo</button>
              </Link>
            </li>
          ))}
        </ul>
        <div className="flex items-center justify-center h-screen">
          <button type="button" className="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-2 rounded mt-2 fixed bottom-10 m-auto"
            onClick={() => setIsModalOpen(true)}>
            Thêm báo cáo
          </button>
        </div>
        {/* Modal */}
        {isModalOpen && (
          <div className="fixed inset-0 bg-gray-600 bg-opacity-50 flex justify-center items-center">
            <div className="bg-white rounded-lg p-6 w-96">
              <h2 className="text-lg font-bold mb-4">Chọn khoảng thời gian lấy báo cáo</h2>
              <div className="mb-4">
                <label className="block text-sm font-bold mb-2">Ngày bắt đầu:</label>
                <input
                  title="from"
                  name="from"
                  type="datetime-local"
                  value={startDate}
                  onChange={(e) => setStartDate(e.target.value)}
                  className="w-full border px-2 py-1 rounded"
                />
              </div>
              <div className="mb-4">
                <label className="block text-sm font-bold mb-2">Ngày kết thúc:</label>
                <input
                  title="to"
                  name="to"
                  type="datetime-local"
                  value={endDate}
                  onChange={(e) => setEndDate(e.target.value)}
                  className="w-full border px-2 py-1 rounded"
                />
              </div>
              <div className="flex justify-end space-x-4">
                <button
                  type="button"
                  onClick={() => setIsModalOpen(false)}
                  className="bg-gray-500 hover:bg-gray-700 text-white font-bold py-1 px-3 rounded"
                >
                  Hủy
                </button>
                <button
                  type="button"
                  onClick={handleSubmit}
                  className="bg-green-500 hover:bg-green-700 text-white font-bold py-1 px-3 rounded"
                >
                  Gửi
                </button>
              </div>
            </div>
          </div>
        )}
      </div>

    </div >

  );
}