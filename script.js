// ✅ URL에서 link 파라미터 읽는 함수
function getLinkParam() {
  const params = new URLSearchParams(window.location.search);
  return params.get('link'); // 없으면 null
}

// ✅ link 파라미터 ↔️ 탭 아이디 매핑
const linkToTab = {
  cslca2ox: 'tab1',
  cxwqbewy: 'tab2',
  code3: 'tab3',
  code4: 'tab4',
  code5: 'tab5',
  code6: 'tab6',
  code7: 'tab7',
  code8: 'tab8'
};
const tabToLink = {
  tab1: 'cslca2ox',
  tab2: 'cxwqbewy',
  tab3: 'code3',
  tab4: 'code4',
  tab5: 'code5',
  tab6: 'code6',
  tab7: 'code7',
  tab8: 'code8'
};

// ✅ 문서 준비 완료
document.addEventListener('DOMContentLoaded', () => {

  // 1️⃣ nav 버튼 active 처리 + 클릭시 이동
  const currentPath = window.location.pathname.split('/').pop();
  document.querySelectorAll('nav button').forEach(button => {
    const buttonHref = button.getAttribute('data-href');
    if (buttonHref === currentPath) {
      button.classList.add('active');
    }
    button.addEventListener('click', () => {
      location.href = buttonHref;
    });
  });

  // 2️⃣ 지도 초기화
  const container = document.getElementById('map');
  let map, marker;
  if (container) { // 지도가 존재할 때만 초기화!
    const options = {
      center: new kakao.maps.LatLng(37.06986, 127.01770),
      level: 3
    };
    map = new kakao.maps.Map(container, options);

    const markerPosition = new kakao.maps.LatLng(37.06986, 127.01770);
    marker = new kakao.maps.Marker({
      position: markerPosition
    });
    marker.setMap(map);
  }

  // 3️⃣ 기본 탭 설정
  const linkParam = getLinkParam();
  const defaultTab = linkToTab[linkParam] || 'tab1';
  activateTab(defaultTab);

  // 문서 로드 직후 (네가 이미 가지고 있던 activateTab 호출 아래!)
if (defaultTab === 'tab2' && map && marker) {
  setTimeout(() => {
    map.relayout();
    const centerPosition = new kakao.maps.LatLng(37.06986, 127.01770);
    map.setCenter(centerPosition);
    marker.setPosition(centerPosition);
  }, 200);
}

  // 4️⃣ 탭 클릭 이벤트
  document.querySelectorAll('.tab').forEach(tab => {
    tab.addEventListener('click', () => {
      const tabId = tab.dataset.tab;
      const newLink = tabToLink[tabId] || 'cslca2ox';

      // URL 파라미터 갱신
      const url = new URL(window.location);
      url.searchParams.set('link', newLink);
      window.history.pushState({}, '', url);

      // 탭 활성화
      activateTab(tabId);

      // 지도 탭일 때 지도 리사이즈/마커 다시 지정
      if (tabId === 'tab2' && map && marker) {
        setTimeout(() => {
          map.relayout();
          const centerPosition = new kakao.maps.LatLng(37.06986, 127.01770);
          map.setCenter(centerPosition);
          marker.setPosition(centerPosition);
        }, 200);
      }
    });
  });

  // 5️⃣ 길찾기/지도에서 보기 버튼
  const findWayBtn = document.getElementById('find-way-btn');
  const viewMapBtn = document.getElementById('view-map-btn');
  if (findWayBtn) {
    findWayBtn.addEventListener('click', () => {
      const placeName = "다원엔지니어링";
      const lat = 37.06986;
      const lng = 127.01770;
      const kakaoMapUrl = `https://map.kakao.com/link/to/${encodeURIComponent(placeName)},${lat},${lng}`;
      window.open(kakaoMapUrl, '_blank');
    });
  }
  if (viewMapBtn) {
    viewMapBtn.addEventListener('click', () => {
      const placeName = "다원엔지니어링";
      const lat = 37.06986;
      const lng = 127.01770;
      const kakaoMapUrl = `https://map.kakao.com/link/map/${encodeURIComponent(placeName)},${lat},${lng}`;
      window.open(kakaoMapUrl, '_blank');
    });
  }

});

// ✅ 탭 활성화 함수
function activateTab(tabId) {
  document.querySelectorAll('.tab').forEach(tab => {
    tab.classList.remove('active');
  });
  document.querySelectorAll('.tab-content').forEach(content => {
    content.classList.remove('active');
  });
  const activeTab = document.querySelector(`.tab[data-tab="${tabId}"]`);
  const activeContent = document.getElementById(tabId);
  if (activeTab && activeContent) {
    activeTab.classList.add('active');
    activeContent.classList.add('active');
  }
}


// 견적문의_ 모달 창

  // 요소 선택
  const modal = document.getElementById("privacyModal");
  const openBtn = document.getElementById("privacyLink");
  const closeBtn = document.getElementById("closeBtn");
  const closeSpan = document.querySelector(".close");

  // 열기
  openBtn.onclick = function(e) {
    e.preventDefault(); // 기본 동작 방지
    modal.style.display = "block";
  }

  // 닫기
  closeBtn.onclick = function() {
    modal.style.display = "none";
  }
  closeSpan.onclick = function() {
    modal.style.display = "none";
  }

  // 바깥 영역 클릭하면 닫기
  window.onclick = function(event) {
    if (event.target === modal) {
      modal.style.display = "none";
    }
  }
