package org.trams.common.job;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.axis.types.Time;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.trams.bean.jpa.CommentNotification;
import org.trams.business.service.CommentService;
import org.trams.business.service.PurchaseService;
import org.trams.business.service.SettingService;
import org.trams.business.service.TimeLearnService;
import org.trams.rest.common.notification.Notification;
import org.trams.web.common.ApplicationDefine;
import org.trams.web.common.utils.DataUtils;

public class ExecuteJob {

	@Resource
	public PurchaseService purchaseService;

	@Resource
	public TimeLearnService timeLearnService;

	@Resource
	public SettingService settingService;

	@Scheduled(cron = "0 0 0 * * *")
	public void updateRefundEndday() {
		purchaseService.updateRefundEndday();
	}

	@Scheduled(cron = "0 0 12 * * *")
	public void sendCouponNotification() {
		List<String> list_join_1 = settingService.getFCMTokenBy_CouponNotification_Join(1);
		List<String> list_join_7 = settingService.getFCMTokenBy_CouponNotification_Join(7);
		List<String> list_use_1 = settingService.getFCMTokenBy_CouponNotification_Use_Comment(5, 1);
		List<String> list_use_7 = settingService.getFCMTokenBy_CouponNotification_Use_Comment(5, 7);
		List<String> list_comment_1 = settingService.getFCMTokenBy_CouponNotification_Use_Comment(6, 1);
		List<String> list_comment_7 = settingService.getFCMTokenBy_CouponNotification_Use_Comment(6, 7);

		if (list_join_1 != null) {
			Notification n = new Notification();
			n.sendNotification(list_join_1, "추천 프로그램 개강 알림", "가입 감사 쿠폰의 사용기간이 하루 남았습니다. 확인 후 사용해 주세요!", null,
					ApplicationDefine.NOTIFICATION_CouponNotification, 1);
		}

		if (list_join_7 != null) {
			Notification n = new Notification();
			n.sendNotification(list_join_7, "추천 프로그램 개강 알림", "가입 감사 쿠폰의 사용기간이 7일 남았습니다. 확인 후 사용해 주세요!", null,
					ApplicationDefine.NOTIFICATION_CouponNotification, 1);
		}

		if (list_use_1 != null) {
			Notification n = new Notification();
			n.sendNotification(list_use_1, "추천 프로그램 개강 알림", "이용 감사 쿠폰의 사용기간이 하루 남았습니다. 확인 후 사용해 주세요!", null,
					ApplicationDefine.NOTIFICATION_CouponNotification, 1);
		}

		if (list_use_7 != null) {
			Notification n = new Notification();
			n.sendNotification(list_use_7, "추천 프로그램 개강 알림", "이용 감사 쿠폰의 사용기간이 7일 남았습니다. 확인 후 사용해 주세요!", null,
					ApplicationDefine.NOTIFICATION_CouponNotification, 1);
		}

		if (list_comment_1 != null) {
			Notification n = new Notification();
			n.sendNotification(list_use_1, "추천 프로그램 개강 알림", "후기 감사 쿠폰의 사용기간이 하루 남았습니다. 확인 후 사용해 주세요!", null,
					ApplicationDefine.NOTIFICATION_CouponNotification, 1);
		}

		if (list_comment_7 != null) {
			Notification n = new Notification();
			n.sendNotification(list_use_7, "추천 프로그램 개강 알림", "후기 감사 쿠폰의 사용기간이 7일 남았습니다. 확인 후 사용해 주세요!”", null,
					ApplicationDefine.NOTIFICATION_CouponNotification, 1);
		}
	}

	@Scheduled(fixedRate = 60000)
	public void SendCommentNotification() {
		Date now = DataUtils.getNowDate();
		String str_now = DataUtils.convertDate_To_String(now, "yyyy-MM-dd HH:mm");
		List<CommentNotification> list = settingService.getFCMTokenBy_CommentNotification(str_now);
		List<String> list_reg = new ArrayList<>();
		for (CommentNotification i : list) {
			Notification n = new Notification();
			list_reg.add(i.getFCMToken());
			n.sendNotification(list_reg, "후기 작성 알림", "수강하신 "+i.getTitle() + " 은 어떠셨나요? 지금 후기를 남기고, 할인 쿠폰을 받아가세요!", i.getId(), ApplicationDefine.NOTIFICATION_CommentNotification, 1);
		}

	}

	@Scheduled(fixedRate = 3600000)
	public void FinishRegisterProgramNotification() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, -1);
		Date now = DataUtils.getNowDate();
		List<CommentNotification> list = settingService.getFCMTokenBy_FinishRegisterCourseNotification(now,
				c.get(Calendar.HOUR));
		Integer program_id = 0;
		String program_title = "";
		if (list != null) {
			for (CommentNotification i : list) {
				List<String> list_reg = new ArrayList<>();
				list_reg.add(i.getFCMToken());
				Notification n = new Notification();
				n.sendNotification(list_reg, "내 프로그램에 수강신청이 완료되었을 때", i.getTitle() + " 에 대한 수강신청이 등록되었습니다. 확인해 주세요!",
						i.getId(), ApplicationDefine.NOTIFICATION_FinishRegisterCourseNotification, 1);
			}
		}
	}
}
