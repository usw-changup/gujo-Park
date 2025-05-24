// URL에서 link 파라미터 읽는 함수
function getLinkParam() {
  const params = new URLSearchParams(window.location.search);
  return params.get('link'); // 없으면 null
}

// 탭 활성화 함수
function activateTab(tabId) {
  document.querySelectorAll('.tab').forEach(tab => {
    tab.classList.remove('active');
  });
  document.querySelectorAll('.tab-content').forEach(content => {
    content.classList.remove('active');
  });

  document.querySelector(`.tab[data-tab="${tabId}"]`).classList.add('active');
  document.getElementById(tabId).classList.add('active');
}

// 페이지 로드시 실행
document.addEventListener('DOMContentLoaded', () => {
  const linkParam = getLinkParam();

  // 기본 탭 설정
  let defaultTab = 'tab2';
  if (linkParam === 'cslca2ox') {
    defaultTab = 'tab1';
  } else if (linkParam === 'cxwqbewy') {
    defaultTab = 'tab2';
  }

  activateTab(defaultTab);

  // 탭 클릭 시 URL 파라미터도 바꿔주기
  document.querySelectorAll('.tab').forEach(tab => {
    tab.addEventListener('click', () => {
      const tabId = tab.getAttribute('data-tab');
      let newLink = '';
      if (tabId === 'tab1') {
        newLink = 'cslca2ox';
      } else if (tabId === 'tab2') {
        newLink = 'cxwqbewy';
      }
      const url = new URL(window.location);
      url.searchParams.set('link', newLink);
      window.history.pushState({}, '', url);

      activateTab(tabId);
    });
  });
});
