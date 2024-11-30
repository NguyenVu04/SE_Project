'use client';
import TopBar from "@/components/ui/topbar";
export default function ReportPage() {
    
    const reports = [
  {
    filename: "report1",
    url: "https://localhost:8080/report1",
    fileSize: 1000,
    uploadDate: "2022-01-01T00:00:00"
  },
  {
    filename: "report2",
    url: "https://localhost:8080/report2",
    fileSize: 2000,
    uploadDate: "2022-01-02T00:00:00"
  },
  {
    filename: "report3",
    url: "https://localhost:8080/report3",
    fileSize: 1500,
    uploadDate: "2022-01-03T00:00:00"
  },
  {
    filename: "report4",
    url: "https://localhost:8080/report4",
    fileSize: 1800,
    uploadDate: "2022-01-04T00:00:00"
  },
  {
    filename: "report5",
    url: "https://localhost:8080/report5",
    fileSize: 1200,
    uploadDate: "2022-01-05T00:00:00"
  },
  {
    filename: "report6",
    url: "https://localhost:8080/report6",
    fileSize: 2500,
    uploadDate: "2022-01-06T00:00:00"
  },
  {
    filename: "report7",
    url: "https://localhost:8080/report7",
    fileSize: 3000,
    uploadDate: "2022-01-07T00:00:00"
  },
  {
    filename: "report8",
    url: "https://localhost:8080/report8",
    fileSize: 2200,
    uploadDate: "2022-01-08T00:00:00"
  },
  {
    filename: "report9",
    url: "https://localhost:8080/report9",
    fileSize: 2700,
    uploadDate: "2022-01-09T00:00:00"
  },
  {
    filename: "report10",
    url: "https://localhost:8080/report10",
    fileSize: 3200,
    uploadDate: "2022-01-10T00:00:00"
  }
    ];

    return ( 
        <div>
            <TopBar/>
            <div className="bg-gradient-to-b from-[#0381FF] to-[#02067A] h-screen">
                <div
                    id="main-content"
                    className="flex-grow flex flex-col items-center justify-center text-white"
                >
                    <h1 className="text-6xl text-orange-600 mt-5">Report page</h1>
                    <p className="mt-5 mb-5 text-xl">
                        View and download reports here.
                    </p>
                    
                </div>
                {/* Render report */}
                <ul className="grid grid-cols-3 gap-4 mx-4">
                    {reports.map((report, index) => (
                    <li key={index} className="mb-4 border p-2 bg-white shadow-xl rounded-lg">
                        <strong>Filename:</strong> {report.filename} <br />
                        <strong>URL:</strong> <a href={report.url} target="_blank" rel="noopener noreferrer" className="text-green-600 underline">{report.url}</a> <br />
                        <strong>File Size:</strong> {report.fileSize} bytes <br />
                        <strong>Upload Date:</strong> {new Date(report.uploadDate).toLocaleString()}
                    </li>
                    ))}
                </ul>
            </div>
        </div>
        
    );
}