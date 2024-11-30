import React from 'react';
import { useState } from 'react';
const TopBar = () => {
  // State to manage modal visibility
  const [isStudentFormOpen, setStudentFormOpen] = useState(false);
  const [isSpsoFormOpen, setSpsoFormOpen] = useState(false);
  const [isStudentDeleteOpen, setStudentDeleteOpen] = useState(false);
  // Function to handle opening and closing the modal
  const toggleStudentForm = () => {
    setStudentFormOpen(!isStudentFormOpen);
  };
  const toggleSpsoForm = () => {
    setSpsoFormOpen(!isSpsoFormOpen);
  };
  const toggleStudentDeleteForm = () => {
    setStudentDeleteOpen(!isStudentDeleteOpen);
  };
  return (
    <div className="bg-white shadow grid grid-cols-5 grid-rows-1  p-4 fixed top-0 left-64 w-[calc(100%-256px)] z-50 round-xl">
      {/* Left Section: Title */}
        <h1 className="text-xl font-semibold text-gray-800">Bảng điều khiển</h1>
        {/* Right Section: Add Button */}
        <button className=" ml-3 px-4 py-2 bg-hcmut-dark text-white rounded-md shadow hover:bg-hcmut-light focus:ring-2 focus:ring-blue-400"
          onClick={toggleStudentForm}>
            + Thêm sinh viên
        </button>
        <button className="ml-3 px-4 py-2 bg-red-600 hover:bg-black text-white rounded-md shadow  "
          onClick={toggleStudentDeleteForm}>
            - Xóa sinh viên
        </button>  
        <button className=" ml-3 px-4 py-2 bg-hcmut-dark text-white rounded-md shadow hover:bg-hcmut-light focus:ring-2 focus:ring-blue-400"
          onClick={toggleSpsoForm}>
            + Thêm SPSO
        </button>
        <button className="ml-3 px-4 py-2 bg-hcmut-dark text-white rounded-md shadow hover:bg-hcmut-light focus:ring-2 focus:ring-blue-400">
            + Thêm máy in
        </button>    
          
        {isStudentFormOpen && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
          <div className="bg-white p-6 rounded-lg shadow-lg w-96">
            <h2 className="text-lg font-semibold mb-4">Thêm sinh viên</h2>
            <form
              onSubmit={(e) => {
                e.preventDefault();
                // Add your form submission logic here
                console.log("Student created!");
                toggleStudentForm(); // Close the modal after submission
              }}
            >
              
              <div className="mb-4">
                <label className="block text-gray-700">Email</label>
                <input
                  type="email"
                  className="w-full border border-gray-300 p-2 rounded-md"
                  placeholder="Nhập email"
                />
              </div>
              <div className="mb-4">
                <label className="block text-gray-700">Tên</label>
                <input
                  type="text"
                  className="w-full border border-gray-300 p-2 rounded-md"
                  placeholder="Nhập tên của sinh viên"
                />
              </div>
              <div className="mb-4">
                <label className="block text-gray-700">Họ và tên lót</label>
                <input
                  type="text"
                  className="w-full border border-gray-300 p-2 rounded-md"
                  placeholder="Nhập họ và tên lót của sinh viên"
                />
              </div>
              <div className="mb-4">
                <label className="block text-gray-700">Ngày sinh</label>
                <input
                  type="date"
                  className="w-full border border-gray-300 p-2 rounded-md"
                  placeholder="Chọn ngày sinh của sinh viên"
                />
              </div>
              <div className="mb-4">
                <label className="block text-gray-700">Số dư</label>
                <input
                  type="number"
                  className="w-full border border-gray-300 p-2 rounded-md"
                  placeholder="Nhập số dư của sinh viên"
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
              onSubmit={(e) => {
                e.preventDefault();
                // Add your form submission logic here
                console.log("Student created!");
                toggleSpsoForm(); // Close the modal after submission
              }}
            >
              
              <div className="mb-4">
                <label className="block text-gray-700">Email</label>
                <input
                  type="email"
                  className="w-full border border-gray-300 p-2 rounded-md"
                  placeholder="Nhập email"
                />
              </div>
              <div className="mb-4">
                <label className="block text-gray-700">Tên</label>
                <input
                  type="text"
                  className="w-full border border-gray-300 p-2 rounded-md"
                  placeholder="Nhập tên của sinh viên"
                />
              </div>
              <div className="mb-4">
                <label className="block text-gray-700">Họ và tên lót</label>
                <input
                  type="text"
                  className="w-full border border-gray-300 p-2 rounded-md"
                  placeholder="Nhập họ và tên lót của sinh viên"
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
        {isStudentDeleteOpen && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
          <div className="bg-white p-6 rounded-lg shadow-lg w-96">
            <h2 className="text-lg font-semibold mb-4">Xóa sinh viên</h2>
            <form
              onSubmit={(e) => {
                e.preventDefault();
                // Add your form submission logic here
                console.log("Student deleted!");
                toggleStudentDeleteForm(); // Close the modal after submission
              }}
            >
              
              <div className="mb-4">
                <label className="block text-gray-700">Student ID</label>
                <input
                  type="number"
                  className="w-full border border-gray-300 p-2 rounded-md"
                  placeholder="Nhập ID của sinh viên"
                />
              </div>
              <div className="flex justify-end">
                <button
                  type="button"
                  className="px-4 py-2 bg-gray-300 text-gray-700 rounded-md mr-2"
                  onClick={toggleStudentDeleteForm}
                >
                  Hủy
                </button>
                <button
                  type="submit"
                  className="px-4 py-2 bg-red-600 hover:bg-black text-white rounded-md"
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
