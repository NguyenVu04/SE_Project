'use client';
import TopBar from "@/components/ui/topbar";
import getAllReports from "@/lib/get-all-reports";
import Link from "next/link";
import { useEffect, useState } from "react";

export type Report = {
  filename: string;
  url: string;
  fileSize: number;
  uploadDate: string;
}
export default function ReportPage() {

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

  //   const reports = [
  // {
  //   filename: "report1",
  //   url: "https://localhost:8080/report1",
  //   fileSize: 1000,
  //   uploadDate: "2022-01-01T00:00:00"
  // },
  // {
  //   filename: "report2",
  //   url: "https://localhost:8080/report2",
  //   fileSize: 2000,
  //   uploadDate: "2022-01-02T00:00:00"
  // },
  // {
  //   filename: "report3",
  //   url: "https://localhost:8080/report3",
  //   fileSize: 1500,
  //   uploadDate: "2022-01-03T00:00:00"
  // },
  // {
  //   filename: "report4",
  //   url: "https://localhost:8080/report4",
  //   fileSize: 1800,
  //   uploadDate: "2022-01-04T00:00:00"
  // },
  // {
  //   filename: "report5",
  //   url: "https://localhost:8080/report5",
  //   fileSize: 1200,
  //   uploadDate: "2022-01-05T00:00:00"
  // },
  // {
  //   filename: "report6",
  //   url: "https://localhost:8080/report6",
  //   fileSize: 2500,
  //   uploadDate: "2022-01-06T00:00:00"
  // },
  // {
  //   filename: "report7",
  //   url: "https://localhost:8080/report7",
  //   fileSize: 3000,
  //   uploadDate: "2022-01-07T00:00:00"
  // },
  // {
  //   filename: "report8",
  //   url: "https://localhost:8080/report8",
  //   fileSize: 2200,
  //   uploadDate: "2022-01-08T00:00:00"
  // },
  // {
  //   filename: "report9",
  //   url: "https://localhost:8080/report9",
  //   fileSize: 2700,
  //   uploadDate: "2022-01-09T00:00:00"
  // },
  // {
  //   filename: "report10",
  //   url: "https://localhost:8080/report10",
  //   fileSize: 3200,
  //   uploadDate: "2022-01-10T00:00:00"
  // }
  //   ];
  
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");

  const handleSubmit = () => {
    if (!startDate || !endDate) {
      alert("Please fill in both dates.");
      return;
    }
    // Example: Send data to the backend
    // fetch "/api/report"
  };

  return (
    <div>
      <TopBar />
      <div className="bg-gradient-to-b from-[#0381FF] to-[#02067A] h-screen">
        <div
          id="main-content"
          className="flex-grow flex flex-col items-center justify-center text-white"
        >
          <h1 className="text-6xl text-white m-5 uppercase">danh sách báo cáo</h1>

        </div>
        {/* Render report */}
        <ul className="grid grid-cols-3 gap-4 mx-4">
          {reports.map((report, index) => (
            // <li key={index} className="mb-4 border p-2 bg-white shadow-xl rounded-lg">
            //   <strong>Tên báo cáo:</strong> {report.filename} <br />
            //   <strong>Kích thước:</strong> {report.fileSize} bytes <br />
            //   <strong>Ngày tạo:</strong> {new Date(report.uploadDate).toLocaleString()}
            //   <Link href={report.url} target="_blank">
            //     <button type="button" className="w-full bg-green-600 hover:bg-blue-600 text-white py-2 rounded mt-2">Tải báo cáo</button>
            //   </Link>
            // </li>
            <li key={index} className="bg-white">
              <table className="w-full">
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
              </table>
              <Link href={report.url} target="_blank">
                <button type="button" className="w-full bg-green-800 hover:bg-green-600 text-white py-2">Tải báo cáo</button>
              </Link>
            </li>
          ))}
        </ul>
          <div className="flex items-center justify-center h-screen">
            < button className="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-2 rounded mt-2"
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
                      type="date"
                      value={startDate}
                      onChange={(e) => setStartDate(e.target.value)}
                      className="w-full border px-2 py-1 rounded"
                    />
                  </div>
                  <div className="mb-4">
                    <label className="block text-sm font-bold mb-2">Ngày kết thúc:</label>
                    <input
                      type="date"
                      value={endDate}
                      onChange={(e) => setEndDate(e.target.value)}
                      className="w-full border px-2 py-1 rounded"
                    />
                  </div>
                  <div className="flex justify-end space-x-4">
                    <button
                      onClick={() => setIsModalOpen(false)}
                      className="bg-gray-500 hover:bg-gray-700 text-white font-bold py-1 px-3 rounded"
                    >
                      Hủy
                    </button>
                    <button
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