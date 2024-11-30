import DocumentUpload from '@/components/DocumentUpload'
import React from 'react'

const PrintPage = () => {
  return (
    <div
      className="bg-gray-100 w-screen min-h-screen flex flex-col bg-gradient-to-b from-[#0381FF] to-[#02067A]">
        <DocumentUpload />
    </div>
  )
}

export default PrintPage;