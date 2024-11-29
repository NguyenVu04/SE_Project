import React from 'react';

const Notifications = () => {
  const notifications = [
    { message: 'Máy in B, Máy in C sắp hết giấy', type: 'warning' },
    { message: 'Máy in D đã hết mực in', type: 'error' },
  ];

  return (
    <div className="bg-white p-4 rounded shadow">
      <h2 className="text-gray-600 mb-4">Thông báo</h2>
      <ul>
        {notifications.map((note, index) => (
          <li
            key={index}
            className={`p-2 rounded ${
              note.type === 'warning' ? 'bg-yellow-100 text-yellow-700' : 'bg-red-100 text-red-700'
            } mb-2`}
          >
            {note.message}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Notifications;
