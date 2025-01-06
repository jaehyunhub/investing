import Image from 'next/image';

export default function Page() {
  return (
    <main className="w-full min-h-screen bg-black">
      <div className="relative w-full">
        <div className="absolute top-8 left-1/2 transform -translate-x-1/2 z-10 text-white text-center">
          <div className="flex items-center">
          <Image
              src="/images/newlogo.png"
              alt="Sukaworld"
              width={32}
              height={32}
              className="h-8 mx-auto "
            />
            <span className="block mt-2 ml-3 text-4xl font-medium">슈카월드</span>
          </div>
        </div>
        <div className="flex flex-col md:flex-row w-full min-h-screen">
          <div className="relative w-full md:w-1/2 h-screen">
            <div className="absolute inset-0 bg-black/20 z-10" />
            <Image
              src="/images/newsukaworld.png"
              alt="Snowy mountain camping scene"
              fill
              sizes="(max-width: 768px) 100vw, 50vw"
              priority
              className="object-cover w-full h-full"
            />
            <div className="absolute inset-0 flex items-center justify-center z-20">
              <div className="text-center text-white">
                <h2 className="text-4xl md:text-5xl mb-2 font-medium">
                  슈카월드
                </h2>
                <p className="text-xl md:text-2xl font-light">자세히보기</p>
              </div>
            </div>
          </div>
          <div className="relative w-full md:w-1/2 h-screen">
            <div className="absolute inset-0 bg-black/20 z-10" />
            <Image
              src="/images/stockisnow.jpg"
              alt="Mountain apparel scene"
              fill
              sizes="(max-width: 768px) 100vw, 50vw"
              priority
              className="object-cover w-full h-full"
            />
            <div className="absolute inset-0 flex items-center justify-center z-20">
              <div className="text-center text-white">
                <h2 className="text-4xl md:text-5xl font-medium mb-2">
                 슈카코믹스
                </h2>
                <p className="text-xl md:text-2xl font-light">자세히 보기</p>
              </div>
            </div>
          </div>
        </div>
      </div>
         {/* Footer 추가 */}
         <div className="absolute bottom-10 left-1/2 transform -translate-x-1/2 z-10">
          <span className="text-white text-xl opacity-70">© Syuka Friends Inc</span>
        </div>
    </main>
  );
}
