public class Solution1 {

	public static void main(String[] args) {
		long number = 2_147_483_648l;
		Solution1 s1 = new Solution1();
		long[] answer = s1.solution(number);
		System.out.println(answer);
	}

	private static long[] solution(long n) {
		long[] answer = {-1l, -1l};
		int half = (int) (n / 2);
		boolean[] prime = new boolean[half + 1];

		for (int i = 2; i < prime.length; i++) {
			prime[i] = true;
		}

		for (int i = 2; i < prime.length; i++) {
			for (int j = i * i; j < prime.length; j += i) {
				prime[j] = false;
			}
		}

		for (int i = 2; i < prime.length; i++) {
			if (!prime[i]) {
				continue;
			}

			int res = (int) (n / i);
			if ((int) (n % i) == 0 && prime[res]) {
				answer[0] = Math.min(i, res);
				answer[1] = Math.max(i, res);
				break;
			}
		}
		return answer;
	}
}
