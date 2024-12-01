import deleteSpso from '@/lib/delete-spso';
import getAllSPSOs from '@/lib/get-all-spsos';
import React from 'react';

interface Spso {
  id: string;
  email: string;
  firstName: string;
  lastName: string;
}
interface SpsoListProps {
  spsos_prob: Spso[];
  onDeleteSpso: (spsos: Spso[]) => void;
}

const SpsoList: React.FC<SpsoListProps> = ({ spsos_prob, onDeleteSpso }) => {
  const spsos = spsos_prob;
  return (
    <div className="bg-white p-4 rounded shadow">
      <h2 className="text-gray-600 mb-4">Danh nhân viên quản lý máy in</h2>
      <ul>
        {spsos.map((spso, index) => (
          <li key={index} className="mb-2">
            <div className="flex justify-between">
              <span>{spso.firstName} {spso.lastName}</span>
            </div>
            <span className="text-sm text-gray-500">{spso.email}</span>
            <button type='button' className='text-white bg-red-500 hover:bg-red-600 block ml-auto mr-auto px-4 py-2 rounded-md'
              onClick={async () => {
                const status = await deleteSpso(spso.id);

                if (status === 200) {
                  const students = await getAllSPSOs();
                  if (students) {
                    onDeleteSpso(students);
                  } else {
                    alert('Có lỗi xảy ra! Vui lòng thử lại');
                  }
                } else {
                  alert('Có lỗi xảy ra! Vui lòng thử lại');
                }
              }}>Xóa</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default SpsoList;
