package com.mhp.coding.challenges.dependency;

import com.mhp.coding.challenges.dependency.inquiry.Inquiry;
import com.mhp.coding.challenges.dependency.inquiry.InquiryService;
import com.mhp.coding.challenges.dependency.notifications.EmailHandler;
import com.mhp.coding.challenges.dependency.notifications.PushNotificationHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
public class InquiryTest {

    @SpyBean
    private EmailHandler emailHandler;

    @SpyBean
    private PushNotificationHandler pushNotificationHandler;

    @Before
    public void setup() throws Exception {
    @Autowired
    private InquiryService inquiryService;    
    @Autowired
    private EmailHandler emailHandlerService;
    @Autowired
    private PushNotificationHandler pushNotificationHandlerService;
    }
    

    @Test
    public void testInquiryHandlers() {
    	
        final Inquiry inquiry = new Inquiry();
        inquiry.setUsername("TestUser");
        inquiry.setRecipient("service@example.com");
        inquiry.setText("Can I have cheezburger?");
     
        inquiryService.create(inquiry);
        emailHandlerService.sendEmail(inquiry);
        pushNotificationHandlerService.sendNotification(inquiry);
        
        verify(emailHandler).sendEmail(eq(inquiry));
        verify(pushNotificationHandler).sendNotification(eq(inquiry));
    }
    
    @After
	public void cleanUp() {
	inquiryService = null;
        emailHandlerService = null;
        pushNotificationHandlerService = null;
	}
}
