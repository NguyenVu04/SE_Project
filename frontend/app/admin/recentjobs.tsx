import React from 'react';

const RecentJobs = () => {
  const jobs = [
    { name: 'Document_A.docx', pages: 12, date: '22/10/2023', user: 'hoang.vo' },
    { name: 'Document_B.docx', pages: 15, date: '21/10/2023', user: 'hoang.vo' },
  ];

  return (
    <div className="bg-white p-4 rounded shadow">
      <h2 className="text-gray-600 mb-4">Lệnh in gần đây</h2>
      <ul>
        {jobs.map((job, index) => (
          <li key={index} className="mb-2">
            <div className="flex justify-between">
              <span>{job.name}</span>
              <span className="text-sm text-gray-500">{job.pages} trang</span>
            </div>
            <span className="text-sm text-gray-500">{job.date} - {job.user}</span>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default RecentJobs;
