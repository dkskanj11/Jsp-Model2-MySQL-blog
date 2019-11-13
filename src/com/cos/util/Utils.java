package com.cos.util;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import com.cos.model.Board;

public class Utils {

	public static void getPreviewContent(List<Board> boards) {

		for (Board board : boards) {

			Document doc = Jsoup.parse(board.getContent());

			Elements ets = doc.select("img");

			if (ets != null) {
				for (Element et : ets) {
					et.remove();
				}

			}
			board.setContent(doc.toString());
		}

	}

	// 미리보기 이미지 가져오기
	public static void getPreviewImg(List<Board> boards) {

		for (Board board : boards) {
			Document doc = Jsoup.parse(board.getContent());
			Element et = doc.selectFirst("img");
			Elements ets = doc.select("a");

			String thumbnail = "";
			if (ets != null) {
				for (Element element : ets) {
					String href = element.attr("href");
					String value = element.text();
					if (href.contains("https://www.youtube.com/watch") && !element.text().equals("")) {
						String video[] = href.split("=");
						String v = video[1];
						thumbnail = "http://i.ytimg.com/vi/" + v + "/sddefault.jpg";
						board.setPreviewImg(thumbnail);
						break;
					}
				}
			}
			if (thumbnail.equals("")) {
				if (et != null) {
					String previewImg = et.attr("src");
					System.out.println("Util : previewImg : " + previewImg);
					board.setPreviewImg(previewImg);
				} else {
					board.setPreviewImg("/blog/img/home-blog/blog-1.jpg");
				}
			}
		}
	}

	// 유튜브 미리보기 세팅하기
	public static void setPreviewYoutube(Board board) {
		Document doc = Jsoup.parse(board.getContent());
		Elements ets = doc.select("a");

		if (ets != null) {
			for (Element et : ets) {
				String href = et.attr("href");

				System.out.println(href);
				if (href.contains("https://www.youtube.com/watch") && !et.text().equals("")) {
					String video[] = href.split("=");
					String v = video[1];
					System.out.println(v);
					String iframe = "<iframe src='https://www.youtube.com/embed/" + v
							+ "' width='600px' height='350px' allowfullscreen/>";
					System.out.println(iframe);
					et.after(iframe);

				}
			}
			board.setContent(doc.toString());
		}

	}

	@Test
	public void previewYoutubeTest() {
		String content = "<a href='https://www.youtube.com/watch?v=Xn6sRgzmSfs'>https://www.youtube.com/watch?v=Xn6sRgzmSfs</a>";
		Document doc = Jsoup.parse(content);
		Elements ets = doc.select("a");

		if (ets != null) {
			for (Element et : ets) {
				String href = et.attr("href");
				String value = et.text();
				System.out.println(value);
				System.out.println(href);
				if (href.contains("https://www.youtube.com/watch")) {
					String video[] = href.split("=");
					String v = video[1];
					System.out.println(v);
					String iframe = "<iframe src='https://www.youtube.com/embed/" + v
							+ "' width='600px' height='350px' allowfullscreen/>";

				}
			}
		}
	}
}
