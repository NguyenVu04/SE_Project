import React from 'react';

const CurrentJobs = () => {
  const jobs = [
    { name: 'Document_A.docx', printer: 'Máy in A', status: 'Đang in' },
    { name: 'Document_B.docx', printer: 'Máy in A', status: 'Đang in' },
  ];

  return (
    <div className="bg-white p-4 rounded shadow">
      <h2 className="text-gray-600 mb-4">Lệnh in hiện tại</h2>
      <ul>
        {jobs.map((job, index) => (
          <li key={index} className="flex justify-between items-center mb-2">
            <span>{job.name} - {job.printer}</span>
            <span className="text-blue-500">{job.status}</span>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default CurrentJobs;
