import deleteStudent from '@/lib/delete-student';
import getAllStudents from '@/lib/get-all-students';
import React from 'react';

interface Student {
  id: string;
  email: string;
  firstName: string;
  lastName: string;
  dateOfBirth: string;
  balance: number;
}

interface StudentListProps {
  students_prob: Student[];
  onDeleteStudent: (students: Student[]) => void;
}

const StudentList: React.FC<StudentListProps> = ({ students_prob, onDeleteStudent }) => {
  const students = students_prob;

  return (
    <div className="bg-white p-4 rounded shadow">
      <h2 className="text-gray-600 mb-4">Danh sinh viên</h2>
      <ul>
        {students.map((student, index) => (
          <li key={index} className="mb-4">
            <div className="flex justify-between">
              <span>{student.firstName} {student.lastName}</span>
              <span className="text-sm text-gray-500">số dư: {student.balance}</span>
            </div>
            <span className="text-sm text-gray-500">email: {student.email}</span>
            <button type='button' className='text-white bg-red-500 hover:bg-red-600 block ml-auto mr-auto px-4 py-2 rounded-md'
              onClick={async () => {
                const status = await deleteStudent(student.id);

                if (status === 200) {
                  const students = await getAllStudents();
                  if (students) {
                    onDeleteStudent(students);
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

export default StudentList;
