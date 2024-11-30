import React from 'react';
import { IoHomeOutline } from "react-icons/io5";
import { FiPrinter } from "react-icons/fi";
import { FaListUl } from "react-icons/fa";

const SideBar = () => {
  return (
    <div className="w-64 h-screen bg-white text-white flex flex-col items-center p-6 border-solid border-2 shadow-xl">
      {/* Header */}
      <div className="mb-10 text-center">
        <p className="text-lg font-semibold text-hcmut-dark">
          Ho Chi Minh City University of Technology
        </p>
        <p className="text-sm font-ligh text-hcmut-light">Student Smart Printing Service</p>
      </div>

      {/* Menu Items */}
      <div className="flex flex-col gap-6 w-full">
        {/* Dashboard */}
        <div 
            className="flex items-center gap-4 cursor-pointer p-3 rounded-md  text-gray-700
             hover:bg-gray-700 hover:text-white transition-colors duration-200">
          <IoHomeOutline size={24}/>
          <p className="text-sm font-medium ">Bảng điều khiển</p>
        </div>

        {/* Print History */}
        <div 
            className="flex items-center gap-4 cursor-pointer p-3 rounded-md  text-gray-700
             hover:bg-gray-700 hover:text-white transition-colors duration-200">
          <FaListUl size={24}/>
          <p className="text-sm font-medium ">Lịch sử lệnh in</p>
        </div>

        {/* Printer List */}
        <div 
            className="flex items-center gap-4 cursor-pointer p-3 rounded-md  text-gray-700
             hover:bg-gray-700 hover:text-white transition-colors duration-200">
          <FiPrinter size={24}/>
          <p className="text-sm font-medium ">Danh sách máy in</p>
        </div>
      </div>
    </div>
  );
};

export default SideBar;
