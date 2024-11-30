import MainFooter from '@/components/MainFooter'
import MainLayout from '@/components/MainLayout'

const Homepage = () => {
  return (
      <div 
        className=" relative min-h-screen flex flex-col bg-gradient-to-b from-[#0381FF] to-[#02067A]">
        <MainLayout />
        <MainFooter />
      </div>
  )
}

export default Homepage