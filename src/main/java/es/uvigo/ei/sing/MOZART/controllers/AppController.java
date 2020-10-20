package es.uvigo.ei.sing.MOZART.controllers;

import es.uvigo.ei.sing.MOZART.utils.CDKEngine;
import es.uvigo.ei.sing.MOZART.utils.Constants;
import es.uvigo.ei.sing.MOZART.utils.PredictionTask;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Log4j2
@Controller
@RequestMapping
public class AppController {

    private final SimpMessagingTemplate template;
    private final CDKEngine CDKEngine = new CDKEngine();
    private final ThreadPoolTaskExecutor executorQueue;
    private final Map<String, PredictionTask> submmitedPredictionTasks = new HashMap<String, PredictionTask>();
    private final ApplicationContext applicationContext;

    @Autowired
    AppController(SimpMessagingTemplate template, ThreadPoolTaskExecutor restExecutorBean, ApplicationContext applicationContext) {
        this.template = template;
        this.executorQueue = restExecutorBean;
        this.applicationContext = applicationContext;
        Constants.constantDescriptors.size();
    }

    @RequestMapping(value = {"/home", ""})
    public ModelAndView index(HttpServletRequest request, Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (!(auth instanceof AnonymousAuthenticationToken)) {
        ModelAndView mav = new ModelAndView("pages/index");
        model.addAttribute("title", "Home");
        mav.addObject("ip", request.getRequestURL().toString());
        mav.addObject("maxSMiles", Constants.maxSmiles);
        return mav;
//        } else {
//            ModelAndView mav = new ModelAndView("pages/login");
//            mav.addObject("ip", request.getRequestURL().toString());
//            return mav;
//        }
    }

    @RequestMapping(value = {"/contact"})
    public ModelAndView contact(HttpServletRequest request, Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (!(auth instanceof AnonymousAuthenticationToken)) {
        ModelAndView mav = new ModelAndView("pages/contact");
        model.addAttribute("title", "Contact");
        mav.addObject("ip", request.getRequestURL().toString());
        return mav;
//        } else {
//            ModelAndView mav = new ModelAndView("pages/login");
//            mav.addObject("ip", request.getRequestURL().toString());
//            return mav;
//        }
    }


    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", false);
        String id = RequestContextHolder.currentRequestAttributes().getSessionId();
        try {
            log.info("PredictionTask Submmited");
            PredictionTask p = new PredictionTask(file, RequestContextHolder.currentRequestAttributes().getSessionId(), applicationContext);
//            p.run();
            executorQueue.execute(p);
            submmitedPredictionTasks.put(id, p);
            result.put("success", true);

        } catch (IOException e) {
            result.put("message",
                    String.format("Error: %s at line %s. Reference error:  %s ", e.getMessage(), e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName())

            );

            e.printStackTrace();
        }

        result.put("fileName", file.getOriginalFilename());
        result.put("id", RequestContextHolder.currentRequestAttributes().getSessionId());
        result.put("data", file.getOriginalFilename());
        log.info("uploaded file " + file.getOriginalFilename());
        return result;

    }


    @ResponseBody
    @RequestMapping(value = "calculateOneSmile", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> calculateOneSmile(@RequestParam("smile") String smile) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", false);
        PredictionTask p = new PredictionTask(Arrays.asList(new String[]{smile}), applicationContext);

        try {
            log.info("PredictionTask Submmited");
            p.run();
            result.put("success", true);
        } catch (Exception e) {
            result.put("message",
                    String.format("Error: %s at line %s. Reference error:  %s ", e.getMessage(), e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getMethodName())

            );
        }


        result.put("data", p.getOutputData());

        result.put("columns", p.getColumns());
        result.put("success", true);
        return result;
    }


    @ResponseBody
    @AfterThrowing
    @RequestMapping(value = "checkState", method = RequestMethod.GET)
    public Map<String, Object> checkState(@RequestParam("id") String id) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        double finished = submmitedPredictionTasks.get(id).getFinished().get();
        result.put("finished", finished);
        if (submmitedPredictionTasks.get(id).getException() != null) {
            Exception e = submmitedPredictionTasks.get(id).getException();
//            result.put("message", String.format("Error: %s at line %s. Class:  %s ", e.getMessage(), e.getStackTrace()[0].getLineNumber(),
//                    e.getStackTrace()[0].getFileName()));

            result.put("message", "Incorrect file format, please check your file content(remember on SMILE per line) and upload again ");
            result.put("success", false);

            e.printStackTrace();
            log.error(e);
        }

        if (submmitedPredictionTasks.get(id).getException() != null) {
            result.put("success", false);
        }

        if (finished == 100 || finished == -1) {

            result.put("data", submmitedPredictionTasks.get(id).getOutputData());
            result.put("columns", submmitedPredictionTasks.get(id).getColumns());
            submmitedPredictionTasks.remove(id);

        }
        return result;

    }


}

