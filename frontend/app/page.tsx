'use client';
import { Button } from "@/components/ui/button";
import { useRouter } from "next/navigation";
export default function Home() {
  const router = useRouter();
  const handleSignInClick = () => {
    router.push("/signin");  // Redirect to the sign in page
  };
  return (
    <div className="flex flex-col w-full h-full fixed items-center justify-center" style={{
      background: 'linear-gradient(to bottom, #0381FF, #02067A)'
    }}>
      <h1 className="text-6xl font-bold text-orange-600 uppercase text-center">chào mừng đến với hệ thống in thông minh</h1>
      <p className="text-white mt-4">Vui lòng đăng nhập để tiếp tục</p>
      <Button className="mt-4 bg-orange-600" onClick={handleSignInClick}>Sign In</Button>
    </div>
  );
}
