import React from 'react';

interface Spso {
    id: string;
    email: string;
    firstName: string;
    lastName: string;
}
interface SpsoListProps {
    spsos_prob: Spso[];
}

const SpsoList: React.FC<SpsoListProps>= ({spsos_prob}) => {
    const spsos = spsos_prob;
  return (
    <div className="bg-white p-4 rounded shadow">
      <h2 className="text-gray-600 mb-4">Danh nhân viên quản lý máy in</h2>
      <ul>
        {spsos.map((spso, index) => (
          <li key={index} className="mb-2">
            <div className="flex justify-between">
              <span>{spso.firstName}-{spso.lastName}</span>
              <span className="text-sm text-gray-500">ID: {spso.id}</span>
            </div>
            <span className="text-sm text-gray-500">{spso.email}</span>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default SpsoList;
