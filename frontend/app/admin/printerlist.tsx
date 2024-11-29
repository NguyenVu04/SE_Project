import React from 'react';

const PrinterList = () => {
  const printers = [
    { name: 'Máy in A', status: 'In màu', user: 'hoang.vo', progress: '120/120 trang' },
    { name: 'Máy in B', status: 'In màu', user: 'hoang.vo', progress: '4/120 trang' },
    { name: 'Máy in C', status: 'In trắng đen', user: 'hoang.vo', progress: '5/160 trang' },
  ];

  return (
    <div className="bg-white p-4 rounded shadow">
      <h2 className="text-gray-600 mb-4">Danh sách máy in</h2>
      <ul>
        {printers.map((printer, index) => (
          <li key={index} className="mb-2">
            <div className="flex justify-between">
              <span>{printer.name}</span>
              <span className="text-sm text-gray-500">{printer.progress}</span>
            </div>
            <span className="text-sm text-gray-500">{printer.status} - {printer.user}</span>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default PrinterList;
