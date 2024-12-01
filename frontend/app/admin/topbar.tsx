'use client';

import addStudent from '@/lib/add-student';
import React from 'react';
import { useState } from 'react';
import { SPSO, Student } from './page';
import getAllStudents from '@/lib/get-all-students';
import getAllSPSOs from '@/lib/get-all-spsos';
import addSpso from '@/lib/add-spso';
const TopBar = (
  { onAddStudent, onAddSPSO }: {
    onAddStudent: (students: Student[]) => void;
    onAddSPSO: (SPSOs: SPSO[]) => void;
  }
) => {
  // State to manage modal visibility
  const [isStudentFormOpen, setStudentFormOpen] = useState(false);
  const [isSpsoFormOpen, setSpsoFormOpen] = useState(false);
  // Function to handle opening and closing the modal
  const toggleStudentForm = () => {
    setStudentFormOpen(!isStudentFormOpen);
  };
  const toggleSpsoForm = () => {
    setSpsoFormOpen(!isSpsoFormOpen);
  };

  return (
    <div className="bg-white shadow grid grid-cols-3 grid-rows-1 p-4 fixed top-0 left-64 w-[calc(100%-256px)] z-50 round-xl">
      {/* Left Section: Title */}
      <h1 className="text-xl font-semibold text-gray-800">Bảng điều khiển</h1>
      {/* Right Section: Add Button */}
      <button type='button' className=" ml-3 px-4 py-2 bg-hcmut-dark text-white rounded-md shadow hover:bg-hcmut-light focus:ring-2 focus:ring-blue-400"
        onClick={toggleStudentForm}>
        + Thêm sinh viên
      </button>
      <button type='button' className=" ml-3 px-4 py-2 bg-hcmut-dark text-white rounded-md shadow hover:bg-hcmut-light focus:ring-2 focus:ring-blue-400"
        onClick={toggleSpsoForm}>
        + Thêm SPSO
      </button>

      {isStudentFormOpen && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
          <div className="bg-white p-6 rounded-lg shadow-lg w-96">
            <h2 className="text-lg font-semibold mb-4">Thêm sinh viên</h2>
            <form
              onSubmit={async (e) => {
                e.preventDefault();
                const formData = new FormData(e.currentTarget);
                const status = await addStudent(formData);
                if (status === 200) {
                  alert('Thêm sinh viên thành công');
                  const students = await getAllStudents();

                  if (students) {
                    onAddStudent(students);
                  } else {
                    alert('Có lỗi xảy ra! Vui lòng thử lại');
                  }

                } else if (status === 409) {

                  alert('Sinh viên đã tồn tại');

                } else {

                  alert('Có lỗi xảy ra! Vui lòng thử lại');

                }
                toggleStudentForm(); // Close the modal after submission
              }}
            >

              <div className="mb-4">
                <label className="block text-gray-700">Email</label>
                <input
                  required
                  type="email"
                  className="w-full border border-gray-300 p-2 rounded-md"
                  placeholder="Nhập email"
                  name='email'
                />
              </div>
              <div className="mb-4">
                <label className="block text-gray-700">Tên</label>
                <input
                  required
                  type="text"
                  className="w-full border border-gray-300 p-2 rounded-md"
                  placeholder="Nhập tên của sinh viên"
                  name='firstName'
                />
              </div>
              <div className="mb-4">
                <label className="block text-gray-700">Họ và tên lót</label>
                <input
                  required
                  type="text"
                  className="w-full border border-gray-300 p-2 rounded-md"
                  placeholder="Nhập họ và tên lót của sinh viên"
                  name='lastName'
                />
              </div>
              <div className="mb-4">
                <label className="block text-gray-700">Ngày sinh</label>
                <input
                  required
                  type="date"
                  className="w-full border border-gray-300 p-2 rounded-md"
                  placeholder="Chọn ngày sinh của sinh viên"
                  name='dateOfBirth'
                />
              </div>
              <div className="mb-4">
                <label className="block text-gray-700">Số dư</label>
                <input
                  required
                  type="number"
                  className="w-full border border-gray-300 p-2 rounded-md"
                  placeholder="Nhập số dư của sinh viên"
                  name='balance'
                />
              </div>
              <div className="flex justify-end">
                <button
                  type="button"
                  className="px-4 py-2 bg-gray-300 text-gray-700 rounded-md mr-2"
                  onClick={toggleStudentForm}
                >
                  Hủy
                </button>
                <button
                  type="submit"
                  className="px-4 py-2 bg-hcmut-dark hover:bg-hcmut-light text-white rounded-md"
                >
                  Lưu
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
      {isSpsoFormOpen && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
          <div className="bg-white p-6 rounded-lg shadow-lg w-96">
            <h2 className="text-lg font-semibold mb-4">Thêm nhân viên quản lý máy in</h2>
            <form
              onSubmit={async (e) => {
                e.preventDefault();
                const formData = new FormData(e.currentTarget);
                const status = await addSpso(formData);

                if (status === 200) {
                  alert('Thêm nhân viên quản lý máy in thành công');
                  const spso = await getAllSPSOs();

                  if (spso) {
                    onAddSPSO(spso);
                  } else {
                    alert('Có lỗi xảy ra! Vui lòng thử lại');
                  }

                } else if (status === 409) {

                  alert('Nhân viên quản lý máy in đã tồn tại');

                } else {

                  alert('Có lỗi xảy ra! Vui lòng thử lại');

                }
                toggleSpsoForm(); // Close the modal after submission
              }}
            >

              <div className="mb-4">
                <label className="block text-gray-700">Email</label>
                <input
                  type="email"
                  className="w-full border border-gray-300 p-2 rounded-md"
                  placeholder="Nhập email"
                  name='email'
                />
              </div>
              <div className="mb-4">
                <label className="block text-gray-700">Tên</label>
                <input
                  type="text"
                  className="w-full border border-gray-300 p-2 rounded-md"
                  placeholder="Nhập tên của nhân viên quản lý máy in"
                  name='firstName'
                />
              </div>
              <div className="mb-4">
                <label className="block text-gray-700">Họ và tên lót</label>
                <input
                  type="text"
                  className="w-full border border-gray-300 p-2 rounded-md"
                  placeholder="Nhập họ và tên lót của nhân viên quản lý máy in"
                  name='lastName'
                />
              </div>
              <div className="flex justify-end">
                <button
                  type="button"
                  className="px-4 py-2 bg-gray-300 text-gray-700 rounded-md mr-2"
                  onClick={toggleSpsoForm}
                >
                  Hủy
                </button>
                <button
                  type="submit"
                  className="px-4 py-2 bg-hcmut-dark hover:bg-hcmut-light text-white rounded-md"
                >
                  Lưu
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default TopBar;
