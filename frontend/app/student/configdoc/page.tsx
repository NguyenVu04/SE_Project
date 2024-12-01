import ConfigDoc from '@/components/ConfigDoc'
import { studentId as id } from '@/lib/student-id'
import React from 'react'
export type OrderInfo = {
  filename: string
  paperSize: string
  pageNumbers: number[],
  singleSided: boolean
  numberOfCopies: number
}

const studentId = id;
const Page = () => {
  return (
    <div
      className="bg-gray-100 w-screen min-h-screen flex flex-col bg-gradient-to-b from-[#0381FF] to-[#02067A]">
        <ConfigDoc studentId={studentId}/>
    </div>
  )
}

export default Page